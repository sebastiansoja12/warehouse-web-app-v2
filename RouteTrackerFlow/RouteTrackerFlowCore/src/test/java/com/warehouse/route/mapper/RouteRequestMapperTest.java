package com.warehouse.route.mapper;

import com.warehouse.route.domain.model.RouteRequest;
import com.warehouse.route.infrastructure.adapter.primary.mapper.RouteRequestMapper;
import com.warehouse.route.infrastructure.adapter.primary.mapper.RouteRequestMapperImpl;
import com.warehouse.route.infrastructure.api.dto.RouteRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class RouteRequestMapperTest {

    private RouteRequestMapper routeRequestMapper;


    @BeforeEach
    void setup() {
        routeRequestMapper = new RouteRequestMapperImpl();
    }


    @Test
    void shouldMapFromRequestToRequestDto() {
        // given
        final RouteRequest routeRequest = RouteRequest.builder()
                .depotCode("TEST")
                .build();
        // when
        final RouteRequestDto routeRequestDto = routeRequestMapper.map(routeRequest);
        // then
        assertThat(routeRequest.getDepotCode()).isEqualTo(routeRequestDto.getDepotCode());
    }

    @Test
    void shouldMapFromRequestDtoToRequest() {
        // given
        final RouteRequestDto routeRequestDto = RouteRequestDto.builder()
                .depotCode("TEST")
                .build();
        // when
        final RouteRequest routeRequest = routeRequestMapper.map(routeRequestDto);
        // then
        assertThat(routeRequest.getDepotCode()).isEqualTo(routeRequestDto.getDepotCode());
    }
}
