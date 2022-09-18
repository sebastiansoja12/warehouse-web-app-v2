package com.warehouse.parcelmanagement.reroute.infrastructure.adapter.primary;


import com.warehouse.parcelmanagement.reroute.domain.model.RerouteRequest;
import com.warehouse.parcelmanagement.reroute.domain.model.RerouteResponse;
import com.warehouse.parcelmanagement.reroute.domain.model.UpdateParcelRequest;
import com.warehouse.parcelmanagement.reroute.domain.port.primary.RerouteTokenPort;
import com.warehouse.parcelmanagement.reroute.domain.vo.ParcelId;
import com.warehouse.parcelmanagement.reroute.domain.vo.ParcelResponse;
import com.warehouse.parcelmanagement.reroute.domain.vo.RerouteTokenResponse;
import com.warehouse.parcelmanagement.reroute.domain.model.Token;
import com.warehouse.parcelmanagement.reroute.infrastructure.adapter.primary.mapper.PrimaryRequestMapper;
import com.warehouse.parcelmanagement.reroute.infrastructure.adapter.primary.mapper.PrimaryResponseMapper;
import com.warehouse.parcelmanagement.reroute.infrastructure.api.RerouteService;
import com.warehouse.parcelmanagement.reroute.infrastructure.api.dto.*;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RerouteTokenServiceAdapter implements RerouteService {

    private final RerouteTokenPort rerouteTokenPort;

    private final PrimaryRequestMapper primaryRequestMapper;

    private final PrimaryResponseMapper primaryResponseMapper;

    @Override
    public RerouteResponseDto sendReroutingInformation(RerouteRequestDto requestDto) {
        final RerouteRequest request = primaryRequestMapper.map(requestDto);
        final RerouteResponse response = rerouteTokenPort.sendReroutingInformation(request);
        return primaryResponseMapper.map(response);
    }

    @Override
    public ParcelResponseDto update(UpdateParcelRequestDto parcelDto) {
        final UpdateParcelRequest parcelRequest = primaryRequestMapper.map(parcelDto);
        final ParcelResponse parcelResponse = rerouteTokenPort.update(parcelRequest);
        return primaryResponseMapper.map(parcelResponse);
    }

    @Override
    public RerouteTokenResponseDto findByToken(TokenDto tokenDto) {
        final Token token = primaryRequestMapper.map(tokenDto);
        final RerouteTokenResponse rerouteTokenResponse = rerouteTokenPort.findByToken(token);
        return primaryResponseMapper.map(rerouteTokenResponse);
    }

    @Override
    public RerouteTokenResponseDto loadByTokenAndParcelId(TokenDto tokenDto, ParcelIdDto parcelIdDto) {
        final Token token = primaryRequestMapper.map(tokenDto);
        final ParcelId parcelId = primaryRequestMapper.map(parcelIdDto);
        final RerouteTokenResponse rerouteTokenResponse = rerouteTokenPort.loadByTokenAndParcelId(token, parcelId);
        return primaryResponseMapper.map(rerouteTokenResponse);
    }
}
