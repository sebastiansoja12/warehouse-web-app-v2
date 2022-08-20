package com.warehouse.service;


import com.warehouse.dto.RerouteRequest;
import com.warehouse.dto.UpdateParcelRequest;
import com.warehouse.entity.Parcel;
import com.warehouse.entity.RerouteToken;
import com.warehouse.repository.ParcelRepository;
import com.warehouse.repository.RerouteTokenRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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

    private static final String PARCEL_ID = "2e191c24-be5c-4b65-ac12-f3d806351f20";

    private static final String EMAIL = "sebastian5152@wp.pl";

    private static final Long SECONDS_TO_EXPIRE = 600L;

    @Test
    @Transactional
    void shouldGenerateRerouteToken() {
        // given: build request
        final RerouteRequest rerouteRequest = createRequest();

        // when
        final RerouteToken rerouteToken = rerouteService.generateReroutingToken(rerouteRequest);

        // then
        assertThat(rerouteToken).isNotNull();
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

        rerouteTokenRepository.save(rerouteToken);

        final Parcel updatedParcel = createUpdatedParcel();

        final UpdateParcelRequest updateParcelRequest = createUpdateParcelRequest(parcel.getId(), updatedParcel);
        // when
        rerouteService.updateParcel(updateParcelRequest);
        // then
        assertThat(parcel.getLastName()).isEqualTo("Novak");
    }

    private UpdateParcelRequest createUpdateParcelRequest(UUID parcelId, Parcel parcel) {
        final UpdateParcelRequest updateParcelRequest = new UpdateParcelRequest();
        updateParcelRequest.setParcel(parcel);
        updateParcelRequest.setId(parcelId);
        updateParcelRequest.setToken(TOKEN);
        return updateParcelRequest;
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
                .build();
    }
    RerouteRequest createRequest() {
        return new RerouteRequest(UUID.fromString(PARCEL_ID), EMAIL);
    }
}
