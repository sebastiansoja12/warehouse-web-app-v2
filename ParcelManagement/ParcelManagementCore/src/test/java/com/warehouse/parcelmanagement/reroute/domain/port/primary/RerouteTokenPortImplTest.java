package com.warehouse.parcelmanagement.reroute.domain.port.primary;

import com.warehouse.parcelmanagement.reroute.domain.model.Token;
import com.warehouse.parcelmanagement.reroute.domain.service.RerouteService;
import com.warehouse.parcelmanagement.reroute.domain.service.RerouteServiceImpl;
import com.warehouse.parcelmanagement.reroute.domain.vo.RerouteTokenResponse;
import com.warehouse.parcelmanagement.reroute.infrastructure.adapter.secondary.exception.RerouteTokenNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Executable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class RerouteTokenPortImplTest {

    private RerouteTokenPort port;
    @Mock
    private RerouteService service;

    private final Integer TOKEN_VALUE = 27150;

    @BeforeEach
    void setUp() {
        port = new RerouteTokenPortImpl(service);
    }

    @Test
    void shouldFindByToken() {

    }
    @Test
    void shouldThrowRerouteTokenNotFoundException() {
        // given
        final Token token = Token.builder()
                .value(TOKEN_VALUE)
                .build();
        // when
        final RerouteTokenResponse response = port.findByToken(token);
        // then
        assertThat(response).isNull();

    }

}
