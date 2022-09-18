package com.warehouse.parcelmanagement.reroute.infrastructure.adapter.primary;


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
public class RerouteTokenServiceAdapter implements RerouteService {

    private final RerouteTokenPort rerouteTokenPort;

    private final RequestMapper requestMapper;

    private final ResponseMapper responseMapper;

    @Override
    public RerouteResponseDto sendReroutingInformation(RerouteRequestDto requestDto) {
        final RerouteRequest request = requestMapper.map(requestDto);
        final RerouteResponse response = rerouteTokenPort.sendReroutingInformation(request);
        return responseMapper.map(response);
    }

    @Override
    public ParcelResponseDto update(ParcelDto parcelDto) {
        return null;
    }
}
