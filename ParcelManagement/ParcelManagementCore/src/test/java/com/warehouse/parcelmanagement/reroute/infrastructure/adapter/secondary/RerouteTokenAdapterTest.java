package com.warehouse.parcelmanagement.reroute.infrastructure.adapter.secondary;

import com.warehouse.mail.domain.service.MailService;
import com.warehouse.parcelmanagement.reroute.domain.model.Token;
import com.warehouse.parcelmanagement.reroute.domain.port.secondary.ParcelRepository;
import com.warehouse.parcelmanagement.reroute.domain.port.secondary.RerouteTokenPort;
import com.warehouse.parcelmanagement.reroute.domain.port.secondary.RerouteTokenRepository;
import com.warehouse.parcelmanagement.reroute.domain.vo.RerouteTokenResponse;
import com.warehouse.parcelmanagement.reroute.infrastructure.adapter.secondary.exception.RerouteTokenNotFoundException;
import com.warehouse.parcelmanagement.reroute.infrastructure.adapter.secondary.mapper.RequestMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Executable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class RerouteTokenAdapterTest {

    @Mock
    private ParcelRepository parcelRepository;

    @Mock
    private MailService mailService;

    @Mock
    private RerouteTokenRepository rerouteTokenRepository;

    @Mock
    private RequestMapper requestMapper;

    @InjectMocks
    private RerouteTokenAdapter rerouteTokenAdapter;

    @Test
    void shouldFindByToken() {

    }

    @Test
    void shouldThrowRerouteTokenNotFoundException() {
        // given
        final Token token = Token.builder()
                .value(12345)
                .build();
        // when
        final RerouteTokenNotFoundException exception = assertThrows(RerouteTokenNotFoundException.class, () ->
        {
            rerouteTokenAdapter.findByToken(token);
        });
        // then
        assertThat(exception.getMessage()).isEqualTo("Reroute token was not found");
    }

    @Test
    void shouldUpdateParcel() {

    }

    @Test
    void shouldLoadByParcelId() {

    }

    @Test
    void shouldNotLoadByParcelId() {

    }

    @Test
    void shouldLoadByTokenAndParcelId() {

    }

    @Test
    void shouldNotLoadByTokenAndParcelId() {

    }

    @Test
    void shouldSendReroutingInformation() {

    }
}
