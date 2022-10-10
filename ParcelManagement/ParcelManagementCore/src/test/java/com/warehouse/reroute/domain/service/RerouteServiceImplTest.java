package com.warehouse.reroute.domain.service;

import com.warehouse.reroute.domain.model.Token;
import com.warehouse.reroute.domain.port.secondary.ParcelPort;
import com.warehouse.reroute.domain.port.secondary.RerouteTokenPort;
import com.warehouse.reroute.domain.port.secondary.RerouteTokenRepository;
import com.warehouse.reroute.domain.vo.RerouteTokenResponse;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RerouteServiceImplTest {


    @Mock
    private RerouteTokenPort rerouteTokenPort;

    @Mock
    private ParcelPort parcelPort;

    @Mock
    private RerouteTokenRepository rerouteTokenRepository;

    private RerouteService service;

    @Test
    @Disabled
    void shouldFindByToken() {
        // given
        final Token token = Token.builder()
                .value(12345)
                .build();
        when(service.findByToken(token)).thenReturn(mock(RerouteTokenResponse.class));
        // when
        final RerouteTokenResponse rerouteTokenResponse = service.findByToken(token);
        // then
        assertThat(rerouteTokenResponse.getToken()).isEqualTo(12345);
    }


    @Test
    void shouldThrowRerouteTokenNotFoundException() {

    }

    @Test
    void shouldLoadByTokenAndParcelId() {

    }

    @Test
    void shouldThrowParcelAndTokenNotFoundException() {

    }

    @Test
    void shouldLoadByParcelId() {

    }

    @Test
    void shouldUpdateParcel() {

    }

    @Test
    void shouldNotUpdateParcelWhenTokenIsExpired() {

    }

    @Test
    void shouldThrowTokenExpiredException() {

    }

    @Test
    void shouldSendReroutingInformation() {

    }

    @Test
    void shouldNotSendReroutingInformationWhenRecipientIsEmpty() {

    }

    @Test
    void shouldThrowConnectionErrorException() {

    }


}
