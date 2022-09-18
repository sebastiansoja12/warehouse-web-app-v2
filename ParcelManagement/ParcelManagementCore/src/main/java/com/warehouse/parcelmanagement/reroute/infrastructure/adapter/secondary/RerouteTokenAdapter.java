package com.warehouse.parcelmanagement.reroute.infrastructure.adapter.secondary;

import com.warehouse.parcelmanagement.reroute.domain.model.*;
import com.warehouse.parcelmanagement.reroute.domain.port.secondary.RerouteTokenPort;
import com.warehouse.parcelmanagement.reroute.domain.port.secondary.RerouteTokenRepository;
import com.warehouse.parcelmanagement.reroute.domain.vo.ParcelId;
import com.warehouse.parcelmanagement.reroute.domain.vo.ParcelResponse;
import com.warehouse.parcelmanagement.reroute.domain.vo.RerouteTokenResponse;
import com.warehouse.parcelmanagement.reroute.infrastructure.adapter.secondary.exception.RerouteTokenNotFoundException;
import com.warehouse.parcelmanagement.reroute.infrastructure.adapter.secondary.mapper.RequestMapper;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RerouteTokenAdapter implements RerouteTokenPort {

    private final RequestMapper requestMapper;

    private final MailCreator mailCreator;

    private final RerouteTokenRepository repository;

    RerouteResponse buildReroutingInformation(RerouteRequest rerouteRequest) {
        final ParcelId parcelId = requestMapper.map(rerouteRequest);
        return repository.saveReroutingToken(parcelId.getParcelId());
    }

    private ParcelNotification parcelNotification(String subject, String recipient, String body) {
        return new ParcelNotification(subject, recipient, body);
    }

    @Override
    public ParcelResponse update(UpdateParcelRequest request) {
        return null;
    }

    @Override
    public RerouteTokenResponse findByToken(Token token) {
        return repository.findByToken(token).orElseThrow(
                () -> new RerouteTokenNotFoundException("Reroute token was not found"));
    }


    @Override
    public RerouteTokenResponse loadByTokenAndParcelId(Token token, ParcelId parcelId) {
        return repository.loadByTokenAndParcelId(token, parcelId).stream().findFirst().orElseThrow(
                () -> new RerouteTokenNotFoundException("Reroute token was not found"));
    }

    @Override
    public RerouteResponse saveReroutingToken(Long parcelId) {
        return repository.saveReroutingToken(parcelId);
    }

    @Override
    public RerouteResponse sendReroutingInformation(RerouteRequest rerouteRequest) {
        return buildReroutingInformation(rerouteRequest);
    }
}
