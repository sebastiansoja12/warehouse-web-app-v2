package com.warehouse.repository;


import com.warehouse.entity.Parcel;
import com.warehouse.entity.RerouteToken;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataJpaTest
public class RerouteTokenRepositoryTest {

    @Autowired
    private RerouteTokenRepository rerouteTokenRepository;

    @Autowired
    private ParcelRepository parcelRepository;

    @Test
    @Transactional
    void shouldReturnRerouteTokenByTokenId() {
        // given
        final RerouteToken rerouteToken = createRerouteToken();

        // when
        rerouteTokenRepository.save(rerouteToken);

        // then
        assertThat(rerouteToken.getId()).isGreaterThan(0);
        // and: check if token is Integer
        assertThat(rerouteToken.getToken()).isInstanceOf(Integer.class);

    }

    @Test
    @Transactional
    void shouldReturnRerouteTokenByParcelId() {
        // given
        final RerouteToken rerouteToken = createRerouteTokenWithoutParcel();
        final Parcel parcel = createParcel();

        // and: save in db
        parcelRepository.save(parcel);

        // and: set parcel to reroute token
        rerouteToken.setParcel(parcel);

        // and: save in db
        rerouteTokenRepository.save(rerouteToken);

        // when
        final Optional<RerouteToken> rerouteToken1 = rerouteTokenRepository
                .findByParcelId(parcel.getId());

        // then
        Assertions.assertTrue(rerouteToken1.isPresent());
        assertThat(rerouteToken1).isNotNull();
        // and: ids of reroute tokens parcel and given parcel are equal
        assertThat(rerouteToken1.get().getParcel().getId())
                .isEqualTo(parcel.getId());
        // and: check if generated token is Integer
        assertThat(rerouteToken.getToken()).isInstanceOf(Integer.class);

    }

    @Test
    @Transactional
    void shouldDeleteRerouteTokenByTokenId() {
        // given
        final RerouteToken rerouteToken = createRerouteToken();

        // and: save in db
        rerouteTokenRepository.save(rerouteToken);

        // when
        rerouteTokenRepository.deleteByToken(rerouteToken.getToken());

        // then
        final Optional<RerouteToken> rerouteToken1 = rerouteTokenRepository.findByToken(rerouteToken.getToken());
        Assertions.assertFalse(rerouteToken1.isPresent());

    }

    RerouteToken createRerouteToken() {
        final RerouteToken rerouteToken = new RerouteToken();
        rerouteToken.setToken(rerouteToken.generateToken());
        rerouteToken.setCreatedDate(Instant.now());
        rerouteToken.setExpiryDate(Instant.now());

        return rerouteToken;
    }

    RerouteToken createRerouteTokenWithoutParcel() {
        final RerouteToken rerouteToken = new RerouteToken();
        rerouteToken.setToken(rerouteToken.generateToken());
        rerouteToken.setCreatedDate(Instant.now());
        rerouteToken.setExpiryDate(Instant.now());
        rerouteToken.setParcel(createParcel());

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
                .build();
    }
}
