package com.warehouse.repository;


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
@Transactional
public class TemporaryRerouteTokenRepositoryTestDto {

    private static final Long PARCEL_ID = 123456L;

    private final static Long SECONDS_TO_EXPIRE = 100L;

    private final static Integer TOKEN = 12345;

    @Autowired
    private TemporaryRerouteTokenRepository temporaryRerouteTokenRepository;

    @Test
    void shouldReturnRerouteTokenByTokenId() {
        // given
        final RerouteToken rerouteToken = createRerouteToken();

        // when
        temporaryRerouteTokenRepository.save(rerouteToken);

        // then
        assertThat(rerouteToken.getId()).isGreaterThan(0);
        // and: check if token is Integer
        assertThat(rerouteToken.getToken()).isInstanceOf(Integer.class);

    }

    @Test
    void shouldReturnRerouteTokenByParcelId() {
        // given
        final RerouteToken rerouteToken = createRerouteToken();

        // and: save in db
        temporaryRerouteTokenRepository.save(rerouteToken);

        // when
        final Optional<RerouteToken> rerouteToken1 = temporaryRerouteTokenRepository
                .findByParcelId(PARCEL_ID);

        // then
        Assertions.assertTrue(rerouteToken1.isPresent());
        assertThat(rerouteToken1.get()).isNotNull();
        // and: ids of reroute tokens shipment and given shipment are equal
        assertThat(rerouteToken1.get().getParcelId())
                .isEqualTo(PARCEL_ID);
        // and: check if generated token is Integer
        assertThat(rerouteToken.getToken()).isInstanceOf(Integer.class);

    }

    @Test
    void shouldDeleteRerouteTokenByTokenId() {
        // given
        final RerouteToken rerouteToken = createRerouteToken();

        // and: save in db
        temporaryRerouteTokenRepository.save(rerouteToken);

        // when
        temporaryRerouteTokenRepository.deleteByToken(TOKEN);

        // then
        final Optional<RerouteToken> rerouteToken1 = temporaryRerouteTokenRepository.findByToken(TOKEN);
        Assertions.assertFalse(rerouteToken1.isPresent());

    }

    RerouteToken createRerouteToken() {
        final RerouteToken rerouteToken = new RerouteToken();
        rerouteToken.setToken(TOKEN);
        rerouteToken.setCreatedDate(Instant.now());
        rerouteToken.setExpiryDate(Instant.now().plusSeconds(SECONDS_TO_EXPIRE));
        rerouteToken.setParcelId(PARCEL_ID);

        return rerouteToken;
    }
}
