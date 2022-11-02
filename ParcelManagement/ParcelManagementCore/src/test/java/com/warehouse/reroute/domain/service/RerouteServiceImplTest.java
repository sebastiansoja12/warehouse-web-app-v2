package com.warehouse.reroute.domain.service;

import com.warehouse.reroute.domain.model.RerouteToken;
import com.warehouse.reroute.domain.model.Token;
import com.warehouse.reroute.domain.port.secondary.ParcelPort;
import com.warehouse.reroute.domain.port.secondary.RerouteTokenPort;
import com.warehouse.reroute.domain.port.secondary.RerouteTokenRepository;
import com.warehouse.reroute.domain.vo.ParcelId;
import com.warehouse.reroute.domain.vo.RerouteTokenResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RerouteServiceImplTest {


    @Mock
    private RerouteService service;

    private final InMemoryRerouteTokenDatabase database = new InMemoryRerouteTokenDatabase();

    @Test
    void shouldFindByToken() {
        // given
        final Token token = Token.builder()
                .value(12345)
                .build();

        final RerouteToken rerouteToken = RerouteToken.builder()
                .token(token.getValue())
                .id(1L)
                .createdDate(Instant.now())
                .expiryDate(Instant.now().plusSeconds(100L))
                .parcelId(100001L)
                .build();

        final RerouteTokenResponse expectedResponse = RerouteTokenResponse.builder()
                .parcelId(new ParcelId(100001L))
                .token(token.getValue())
                .valid(true)
                .build();

        // insert into fake db
        database.insertRerouteToken(rerouteToken);

        // when
        final RerouteToken rerouteTokenResponse = database.findRerouteTokenByTokenValue(token.getValue());
        // then
        assertThat(rerouteTokenResponse.getToken()).isEqualTo(12345);
    }


    @Test
    void shouldThrowRerouteTokenNotFoundException() {

    }

    @Test
    void shouldThrowConnectionErrorException() {

    }


}
