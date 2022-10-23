package com.warehouse.reroute.infrastructure.adapter.primary;

import com.warehouse.reroute.domain.model.RerouteRequest;
import com.warehouse.reroute.domain.model.RerouteResponse;
import com.warehouse.reroute.domain.port.primary.RerouteServicePort;
import com.warehouse.reroute.domain.port.primary.RerouteServicePortImpl;
import com.warehouse.reroute.domain.service.*;
import com.warehouse.reroute.infrastructure.adapter.primary.mapper.PrimaryRequestMapper;
import com.warehouse.reroute.infrastructure.adapter.primary.mapper.PrimaryRequestMapperImpl;
import com.warehouse.reroute.infrastructure.adapter.primary.mapper.PrimaryResponseMapper;
import com.warehouse.reroute.infrastructure.adapter.primary.mapper.PrimaryResponseMapperImpl;
import com.warehouse.reroute.infrastructure.adapter.secondary.ParcelReadRepository;
import com.warehouse.reroute.infrastructure.adapter.secondary.RerouteTokenReadRepository;
import com.warehouse.reroute.infrastructure.api.dto.*;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RerouteTokenServiceAdapterTest {



    private RerouteTokenServiceAdapter adapter;

    @Mock
    private ParcelReadRepository parcelReadRepository;

    @Mock
    private RerouteTokenReadRepository rerouteTokenReadRepository;

    @Mock
    private RerouteService rerouteService;


    @BeforeEach
    void setUp() {
        final PrimaryRequestMapper requestMapper = new PrimaryRequestMapperImpl();
        final PrimaryResponseMapper responseMapper = new PrimaryResponseMapperImpl();
        final ParcelValidatorService parcelValidatorService = new ParcelValidatorServiceImpl(parcelReadRepository);
        final RerouteTokenValidatorService rerouteTokenValidatorService =
                new RerouteTokenValidatorServiceImpl(rerouteTokenReadRepository);
        final RerouteServicePort port = new RerouteServicePortImpl(rerouteService, parcelValidatorService,
                rerouteTokenValidatorService);
        adapter = new RerouteTokenServiceAdapter(port, requestMapper, responseMapper);
    }

    // TODO
    @Test
    void shouldSendReroutingInformation() {
        // given
        final RerouteRequestDto requestDto = new RerouteRequestDto();
        requestDto.setEmail(emailDto());
        requestDto.setParcelId(parcelId());

        // when
        final RerouteResponseDto responseDto = adapter.sendReroutingInformation(requestDto);
        // then
        assertThat(responseDto).isNull();

    }

    private EmailDto emailDto() {
        final EmailDto email = new EmailDto();
        email.setValue("sebastian5152@wp.pl");
        return email;
    }

    private ParcelId parcelId() {
        final ParcelId parcelId = new ParcelId();
        parcelId.setValue(123456L);
        return parcelId;
    }
}
