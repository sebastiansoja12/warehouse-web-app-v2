package com.warehouse.reroute.infrastructure.adapter.secondary;

import com.warehouse.mail.domain.service.MailService;
import com.warehouse.reroute.domain.model.RerouteToken;
import com.warehouse.reroute.domain.model.Token;
import com.warehouse.reroute.domain.port.secondary.RerouteTokenRepository;
import com.warehouse.reroute.domain.vo.ParcelId;
import com.warehouse.reroute.domain.vo.RerouteTokenResponse;
import com.warehouse.reroute.infrastructure.adapter.secondary.mapper.ResponseMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RerouteTokenAdapterTest {

    @Mock
    private MailService mailService;

    @Mock
    private RerouteTokenRepository rerouteTokenRepository;

    @Mock
    private ResponseMapper responseMapper;

    @InjectMocks
    private RerouteTokenAdapter rerouteTokenAdapter;

    @Test
    void shouldFindByToken() {
        // given
        final Token token = Token.builder()
                .value(12345)
                .build();
        final RerouteToken rerouteToken = new RerouteToken();
        final RerouteTokenResponse response = new RerouteTokenResponse(
                token.getValue(), new ParcelId(123456L), true);
        // when
        when(responseMapper.map(rerouteToken)).thenReturn(response);
        when(rerouteTokenRepository.findByToken(token)).thenReturn(rerouteToken);
        final RerouteTokenResponse rerouteTokenResponse = rerouteTokenAdapter.findByToken(token);
        // then
        verify(rerouteTokenRepository, times(1)).findByToken(token);
        assertThat(rerouteTokenResponse.getToken()).isEqualTo(token.getValue());
    }


    @Test
    void shouldSendReroutingInformation() {
        // TODO
    }
}
