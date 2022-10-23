package com.warehouse.reroute.domain.port.primary;

import com.warehouse.reroute.domain.model.Token;
import com.warehouse.reroute.domain.vo.ParcelId;
import com.warehouse.reroute.domain.vo.RerouteTokenResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RerouteServicePortImplTest {

    @Mock
    private RerouteServicePort port;

    private final Integer TOKEN_VALUE = 27150;

    private final Long PARCEL_ID = 123456L;

    @Test
    void shouldFindByToken() {
        // given
        final Token token = Token.builder()
                .value(TOKEN_VALUE)
                .build();
        when(port.findByToken(token)).thenReturn(new RerouteTokenResponse(TOKEN_VALUE, new ParcelId(PARCEL_ID), true));
        // when
        final RerouteTokenResponse response = port.findByToken(token);
        // then
        assertThat(response).isNotNull();
        assertThat(response.getToken()).isEqualTo(TOKEN_VALUE);
    }

    @Test
    void shouldLoadByTokenAndParcelId() {
        // given

        // when

        // then
    }
}
