package com.warehouse.parcelmanagement.reroute.infrastructure.adapter.secondary;

import com.warehouse.parcelmanagement.reroute.domain.model.RerouteRequest;
import com.warehouse.parcelmanagement.reroute.domain.model.RerouteResponse;
import com.warehouse.parcelmanagement.reroute.domain.port.primary.RerouteTokenPort;
import com.warehouse.parcelmanagement.reroute.infrastructure.adapter.primary.mapper.RequestMapper;
import com.warehouse.parcelmanagement.reroute.infrastructure.adapter.primary.mapper.ResponseMapper;
import com.warehouse.parcelmanagement.reroute.infrastructure.api.RerouteService;
import com.warehouse.parcelmanagement.reroute.infrastructure.api.dto.ParcelDto;
import com.warehouse.parcelmanagement.reroute.infrastructure.api.dto.ParcelResponseDto;
import com.warehouse.parcelmanagement.reroute.infrastructure.api.dto.RerouteRequestDto;
import com.warehouse.parcelmanagement.reroute.infrastructure.api.dto.RerouteResponseDto;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RerouteTokenAdapter implements RerouteService {

    private final RequestMapper requestMapper;

    private final ResponseMapper responseMapper;

    private final RerouteTokenPort port;

    @Override
    public RerouteResponseDto sendReroutingInformation(RerouteRequestDto rerouteRequest) {
        return buildReroutingInformation(rerouteRequest);
    }

    @Override
    public ParcelResponseDto update(ParcelDto parcelDto) {
        return null;
    }

    private RerouteResponseDto buildReroutingInformation(RerouteRequestDto rerouteRequestDto) {
        final RerouteRequest rerouteRequest = requestMapper.map(rerouteRequestDto);

        final RerouteResponse response = port.sendReroutingInformation(rerouteRequest);

        final RerouteResponseDto responseDto = responseMapper.map(response);

        return new RerouteResponseDto(responseDto.getValue());
    }
}
