package com.warehouse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.warehouse.reroute.configuration.RerouteTokenTestConfiguration;
import com.warehouse.reroute.domain.model.RerouteRequest;
import com.warehouse.reroute.domain.port.secondary.RerouteTokenPort;
import com.warehouse.reroute.domain.port.secondary.RerouteTokenRepository;
import com.warehouse.reroute.infrastructure.api.RerouteService;
import com.warehouse.reroute.infrastructure.api.dto.RerouteRequestDto;
import com.warehouse.reroute.infrastructure.api.dto.RerouteResponseDto;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ContextConfiguration(classes = RerouteTokenTestConfiguration.class)
@TestExecutionListeners( {
        DependencyInjectionTestExecutionListener.class,
        TransactionDbUnitTestExecutionListener.class,
})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RerouteTokenIntegrationTest {

    @Autowired
    private RerouteService rerouteService;

    @Autowired
    private RerouteTokenRepository rerouteTokenRepository;

    @Autowired
    private RerouteTokenPort rerouteTokenPort;

    final ObjectMapper mapper = new ObjectMapper();


    @Test
    @Disabled
    void shouldSendRerouteRequest() throws IOException {
        shouldSendRerouteRequest("/reroutetoken/reroute_request.json");
    }

    void shouldSendRerouteRequest(String fileName) throws IOException {
        // given
        final RerouteRequestDto rerouteRequest = mapper.readValue(new File(fileName), RerouteRequestDto.class);
        // when
        final RerouteResponseDto response = rerouteService.sendReroutingInformation(rerouteRequest);
        // then
        assertThat(response).isNotNull();
    }
}
