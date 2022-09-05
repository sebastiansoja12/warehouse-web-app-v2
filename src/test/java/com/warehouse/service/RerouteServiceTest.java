package com.warehouse.service;


import com.warehouse.dto.RerouteRequest;
import com.warehouse.dto.TokenValidationRequest;
import com.warehouse.dto.UpdateParcelRequest;
import com.warehouse.entity.Parcel;
import com.warehouse.entity.RerouteToken;
import com.warehouse.enumeration.ParcelType;
import com.warehouse.exceptions.ParcelNotFound;
import com.warehouse.exceptions.WarehouseException;
import com.warehouse.repository.ParcelRepository;
import com.warehouse.repository.RerouteTokenRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertThrows;

@SpringBootTest
@ActiveProfiles("dev")
public class RerouteServiceTest {

    @Autowired
    private RerouteService rerouteService;

    @Autowired
    private ParcelRepository parcelRepository;

    @Autowired
    private RerouteTokenRepository rerouteTokenRepository;

    private static final Integer TOKEN = 12345;

    private static final Integer INVALID_TOKEN = 12346;

    private static final Long PARCEL_ID = 123456L;

    private static final Long INVALID_PARCEL_ID = 12346L;

    private static final String EMAIL = "sebastian5152@wp.pl";

    private static final Long SECONDS_TO_EXPIRE = 600L;

    @BeforeEach
    void tearDown() {
        rerouteTokenRepository.deleteAll();
    }

    @Test
    @Transactional
    void shouldGenerateRerouteToken() {
        // given: build request
        final RerouteRequest rerouteRequest = createRequest();

        // when
        final RerouteToken rerouteToken = rerouteService.generateReroutingToken(rerouteRequest);

        // then
        assertThat(rerouteToken).isNotNull();
        // and: generated token is Integer
        assertThat(rerouteToken.getToken()).isInstanceOf(Integer.class);
    }

    @Test
    @Transactional
    void shouldUpdateParcel() {
        // given
        final Parcel parcel = createParcel();
        parcelRepository.save(parcel);

        final RerouteToken rerouteToken = new RerouteToken();
        rerouteToken.setId(1L);
        rerouteToken.setToken(TOKEN);
        rerouteToken.setParcelId(parcel.getId());
        rerouteToken.setCreatedDate(Instant.now());
        rerouteToken.setExpiryDate(Instant.now().plusSeconds(SECONDS_TO_EXPIRE));

        // and: save token in db
        rerouteTokenRepository.save(rerouteToken);

        final Parcel updatedParcel = createUpdatedParcel();

        final UpdateParcelRequest updateParcelRequest = createUpdateParcelRequest(parcel.getId(), updatedParcel);
        // when
        rerouteService.updateParcel(updateParcelRequest);
        // then
        assertThat(parcel.getLastName()).isEqualTo("Novak");
    }

    @Test
    @Transactional
    void shouldThrowInvalidTokenError() {
        // given
        final String expectedMessage = "Invalid rerouting Token";
        final Parcel parcel = createParcel();
        parcelRepository.save(parcel);

        final RerouteToken rerouteToken = new RerouteToken();
        rerouteToken.setId(1L);
        rerouteToken.setToken(TOKEN);
        rerouteToken.setParcelId(parcel.getId());
        rerouteToken.setCreatedDate(Instant.now());
        rerouteToken.setExpiryDate(Instant.now().plusSeconds(SECONDS_TO_EXPIRE));

        // and: save token in db
        rerouteTokenRepository.save(rerouteToken);

        final Parcel updatedParcel = createUpdatedParcel();

        final UpdateParcelRequest updateParcelRequest =
                createUpdateParcelRequestWithWrongToken(parcel.getId(), updatedParcel, INVALID_TOKEN);
        // when
        final WarehouseException exception = assertThrows(WarehouseException.class, () -> {
            rerouteService.updateParcel(updateParcelRequest);
        });

        // then
        assertThat(expectedMessage).isNotBlank();
        assertThat(expectedMessage).isEqualTo(exception.getMessage());
    }

    @Test
    @Transactional
    void shouldThrowParcelNotFoundIllegalArgumentException() {
        // given
        final Parcel parcel = createParcel();
        parcelRepository.save(parcel);

        final RerouteToken rerouteToken = createRerouteToken();

        // and: save token in db
        rerouteTokenRepository.save(rerouteToken);

        // when
        final NullPointerException exception = assertThrows(NullPointerException.class, () -> {
                rerouteService.updateParcel(null);
        });
        // then
        final String expectedMessage = "Cannot invoke \"com.warehouse.dto.UpdateParcelRequest.getToken()\" because " +
                "\"parcelRequest\" is null";
        assertThat(expectedMessage).isNotBlank();
        assertThat(expectedMessage).isEqualTo(exception.getMessage());
    }

    @Test
    @Transactional
    void shouldThrowWrongParcelIdTypeException() {
        // given
        final Parcel parcel = createParcel();
        parcelRepository.save(parcel);

        final RerouteToken rerouteToken = createRerouteToken();

        // and: save token in db
        rerouteTokenRepository.save(rerouteToken);

        final Parcel updatedParcel = createUpdatedParcel();

        final UpdateParcelRequest updateParcelRequest = createUpdateParcelRequest(INVALID_PARCEL_ID,
                updatedParcel);
        // when
        final ParcelNotFound exception = assertThrows(ParcelNotFound.class, () -> {
            rerouteService.updateParcel(updateParcelRequest);
        });
        // then
        final String expectedMessage = "Parcel was not found";
        assertThat(expectedMessage).isNotBlank();
        assertThat(expectedMessage).isEqualTo(exception.getMessage());
    }

    @Test
    @Transactional
    void shouldValidateToken() {
        // given
        final RerouteToken rerouteToken = createRerouteToken();

        rerouteTokenRepository.save(rerouteToken);

        // when
        final RerouteToken validatedRerouteToken = rerouteService.validateReroutingToken(TOKEN);
        // then
        assertThat(validatedRerouteToken).isInstanceOf(RerouteToken.class);
    }

    @Test
    @Transactional
    void tokenShouldBeExpired() {
        // given: create parcel
        final Parcel parcel = createParcel();

        // and: save in db
        parcelRepository.save(parcel);

        // and: create validation request
        final TokenValidationRequest tokenValidationRequest = new TokenValidationRequest();
        tokenValidationRequest.setToken(TOKEN);
        tokenValidationRequest.setParcelId(parcel.getId());

        // and: create expired token
        final RerouteToken rerouteToken = new RerouteToken();
        rerouteToken.setToken(TOKEN);
        rerouteToken.setParcelId(parcel.getId());
        rerouteToken.setCreatedDate(Instant.now().minusSeconds(SECONDS_TO_EXPIRE));
        rerouteToken.setExpiryDate(Instant.now());

        // and: save in db
        rerouteTokenRepository.save(rerouteToken);

        // when
        final boolean isTokenValid = rerouteService.tokenValidation(tokenValidationRequest);

        // then
        Assertions.assertFalse(isTokenValid);
    }

    @Test
    @Transactional
    void tokenShouldNotBeExpired() {
        // given: create parcel
        final Parcel parcel = createParcel();

        // and: save in db
        parcelRepository.save(parcel);

        // and: create validation request
        final TokenValidationRequest tokenValidationRequest = new TokenValidationRequest();
        tokenValidationRequest.setToken(TOKEN);
        tokenValidationRequest.setParcelId(parcel.getId());

        // and: create expired token
        final RerouteToken rerouteToken = createRerouteToken();
        rerouteToken.setToken(TOKEN);
        rerouteToken.setParcelId(parcel.getId());
        rerouteToken.setCreatedDate(Instant.now());
        rerouteToken.setExpiryDate(Instant.now().plusSeconds(SECONDS_TO_EXPIRE));

        // and: save in db
        rerouteTokenRepository.save(rerouteToken);

        // when
        final boolean isTokenValid = rerouteService.tokenValidation(tokenValidationRequest);

        // then
        Assertions.assertTrue(isTokenValid);
    }

    @Test
    @Transactional
    void shouldDeleteTokenByTokenValue() {
        // given && when
        final RerouteToken rerouteToken = rerouteService.generateReroutingToken(createRequest());
        rerouteService.deleteReroutingToken(rerouteToken.getToken());

        // then
        assertThat(rerouteTokenRepository.findByToken(rerouteToken.getToken())).isEmpty();
    }

    @Test
    @Scheduled(cron = "${purge.cron.expression}")
    void shouldDeleteAllTokensThatExpiredAndWereNotDeleted() {
        // given
        final List<RerouteToken> rerouteTokens = rerouteTokens();
        // when
        rerouteService.purgeExpired();
        // then
        // and: token 12345 doesnt exist
        assertThat(rerouteTokenRepository.findByToken(rerouteTokens.get(0).getToken())).isEmpty();
        // and: token 54321 doesnt exist
        assertThat(rerouteTokenRepository.findByToken(rerouteTokens.get(1).getToken())).isEmpty();
        // and: token 12346 doesnt exist
        assertThat(rerouteTokenRepository.findByToken(rerouteTokens.get(2).getToken())).isEmpty();
    }

    List<RerouteToken> rerouteTokens() {
        final List<RerouteToken> rerouteTokens = new ArrayList<>();
        final RerouteToken rerouteToken1 = new RerouteToken();
        rerouteToken1.setId(1L);
        rerouteToken1.setToken(12345);
        rerouteToken1.setParcelId(PARCEL_ID);
        rerouteToken1.setCreatedDate(Instant.now().minusSeconds(SECONDS_TO_EXPIRE));
        rerouteToken1.setExpiryDate(Instant.now());

        final RerouteToken rerouteToken2 = new RerouteToken();
        rerouteToken2.setId(2L);
        rerouteToken2.setToken(54321);
        rerouteToken2.setParcelId(PARCEL_ID);
        rerouteToken2.setCreatedDate(Instant.now().minusSeconds(SECONDS_TO_EXPIRE + 100L));
        rerouteToken2.setExpiryDate(Instant.now());

        final RerouteToken rerouteToken3 = new RerouteToken();
        rerouteToken3.setId(3L);
        rerouteToken3.setToken(12346);
        rerouteToken3.setParcelId(PARCEL_ID);
        rerouteToken3.setCreatedDate(Instant.now().minusSeconds(SECONDS_TO_EXPIRE + 400L));
        rerouteToken3.setExpiryDate(Instant.now().minusSeconds(100L));

        rerouteTokens.add(rerouteToken1);
        rerouteTokens.add(rerouteToken2);
        rerouteTokens.add(rerouteToken3);

        return rerouteTokens;
    }

    private UpdateParcelRequest createUpdateParcelRequest(Long parcelId, Parcel parcel) {
        final UpdateParcelRequest updateParcelRequest = new UpdateParcelRequest();
        updateParcelRequest.setParcel(parcel);
        updateParcelRequest.setId(parcelId);
        updateParcelRequest.setToken(TOKEN);
        return updateParcelRequest;
    }

    private UpdateParcelRequest createUpdateParcelRequestWithWrongToken(Long parcelId, Parcel parcel, Integer token) {
        final UpdateParcelRequest updateParcelRequest = new UpdateParcelRequest();
        updateParcelRequest.setParcel(parcel);
        updateParcelRequest.setId(parcelId);
        updateParcelRequest.setToken(token);
        return updateParcelRequest;
    }

    private RerouteToken createRerouteToken() {
        final RerouteToken rerouteToken = new RerouteToken();
        rerouteToken.setId(1L);
        rerouteToken.setToken(TOKEN);
        rerouteToken.setParcelId(PARCEL_ID);
        rerouteToken.setCreatedDate(Instant.now());
        rerouteToken.setExpiryDate(Instant.now().plusSeconds(SECONDS_TO_EXPIRE));
        return rerouteToken;
    }

    Parcel createParcel() {
        return Parcel.builder()
                .firstName("Test")
                .lastName("Test")
                .senderTelephone("123456789")
                .senderEmail("test@test.test")
                .recipientFirstName("Test")
                .recipientLastName("Test")
                .recipientEmail("test@test.test")
                .recipientTelephone("123456789")
                .recipientCity("Test")
                .recipientStreet("Test")
                .recipientPostalCode("00-000")
                .parcelType(ParcelType.AVERAGE)
                .build();
    }

    Parcel createUpdatedParcel() {
        return Parcel.builder()
                .firstName("Test")
                .lastName("Novak")
                .senderTelephone("123456789")
                .senderEmail("test@test.test")
                .recipientFirstName("Test")
                .recipientLastName("Test")
                .recipientEmail("test@test.test")
                .recipientTelephone("123456789")
                .recipientCity("Test")
                .recipientStreet("Test")
                .recipientPostalCode("00-000")
                .parcelType(ParcelType.AVERAGE)
                .build();
    }
    RerouteRequest createRequest() {
        return new RerouteRequest(PARCEL_ID, EMAIL);
    }
}
