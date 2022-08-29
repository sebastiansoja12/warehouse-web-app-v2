package com.warehouse.parcelmanagement.reroute.infrastructure.adapter.secondary;

import com.warehouse.parcelmanagement.reroute.domain.model.RerouteRequest;
import com.warehouse.parcelmanagement.reroute.domain.model.RerouteResponse;
import com.warehouse.parcelmanagement.reroute.domain.port.primary.RerouteTokenPort;
import com.warehouse.parcelmanagement.reroute.infrastructure.adapter.primary.mapper.RequestMapper;
import com.warehouse.parcelmanagement.reroute.infrastructure.adapter.primary.mapper.ResponseMapper;
import lombok.AllArgsConstructor;

import java.util.logging.Logger;

@AllArgsConstructor
public class RerouteTokenAdapter implements RerouteTokenPort {

    private final RequestMapper requestMapper;

    private final ResponseMapper responseMapper;

    private final Logger logger;

    private final MailCreator mailCreator;

    @Override
    public RerouteResponse sendReroutingInformation(RerouteRequest rerouteRequest) {
        return buildReroutingInformation(rerouteRequest);
    }

    private RerouteResponse buildReroutingInformation(RerouteRequest rerouteRequest) {
        return null;
    }
}
