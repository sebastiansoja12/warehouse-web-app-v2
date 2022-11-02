package com.warehouse.reroute.infrastructure.adapter.secondary;

import com.warehouse.reroute.domain.model.Token;
import com.warehouse.reroute.domain.model.UpdateParcelRequest;
import com.warehouse.reroute.domain.port.secondary.ParcelPort;
import com.warehouse.reroute.domain.port.secondary.ParcelRepository;
import com.warehouse.reroute.domain.port.secondary.RerouteTokenRepository;
import com.warehouse.reroute.domain.service.RerouteTokenValidatorService;
import com.warehouse.reroute.domain.vo.ParcelResponse;
import com.warehouse.reroute.infrastructure.adapter.secondary.exception.RerouteTokenNotFoundException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ParcelAdapter implements ParcelPort {

    private final ParcelRepository parcelRepository;

    private final RerouteTokenRepository rerouteTokenRepository;


    @Override
    public ParcelResponse update(UpdateParcelRequest request) {
        rerouteTokenRepository.findByToken(Token.builder().value(request.getToken()).build());
        parcelRepository.loadByParcelId(request.getId());
        return parcelRepository.update(request).get();
    }

}
