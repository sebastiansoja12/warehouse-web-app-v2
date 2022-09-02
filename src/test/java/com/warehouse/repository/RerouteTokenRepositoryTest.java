package com.warehouse.repository;


import com.warehouse.entity.RerouteToken;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataJpaTest
@Transactional
public class RerouteTokenRepositoryTest {

    private static final String PARCEL_ID = "13100ff0-d422-4b41-8f66-93ee00478ac2";

    private final static Long SECONDS_TO_EXPIRE = 100L;

    private final static Integer TOKEN = 12345;

    @Autowired
    private RerouteTokenRepository rerouteTokenRepository;

    @Test
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
    void shouldReturnRerouteTokenByParcelId() {
        // given
        final RerouteToken rerouteToken = createRerouteToken();

        // and: save in db
        rerouteTokenRepository.save(rerouteToken);

        // when
        final Optional<RerouteToken> rerouteToken1 = rerouteTokenRepository
                .findByParcelId(PARCEL_ID);

        // then
        Assertions.assertTrue(rerouteToken1.isPresent());
        assertThat(rerouteToken1.get()).isNotNull();
        // and: ids of reroute tokens parcel and given parcel are equal
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
        rerouteTokenRepository.save(rerouteToken);

        // when
        rerouteTokenRepository.deleteByToken(TOKEN);

        // then
        final Optional<RerouteToken> rerouteToken1 = rerouteTokenRepository.findByToken(TOKEN);
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
