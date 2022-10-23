package com.warehouse.reroute.domain.port.primary;

import com.warehouse.reroute.domain.exception.ParcelNotFoundException;
import com.warehouse.reroute.domain.model.RerouteRequest;
import com.warehouse.reroute.domain.model.RerouteResponse;
import com.warehouse.reroute.domain.model.Token;
import com.warehouse.reroute.domain.model.UpdateParcelRequest;
import com.warehouse.reroute.domain.service.ParcelValidatorService;
import com.warehouse.reroute.domain.service.RerouteService;
import com.warehouse.reroute.domain.service.RerouteTokenValidatorService;
import com.warehouse.reroute.domain.vo.ParcelId;
import com.warehouse.reroute.domain.vo.ParcelResponse;
import com.warehouse.reroute.domain.vo.RerouteTokenResponse;
import com.warehouse.reroute.infrastructure.adapter.secondary.exception.RerouteTokenNotFoundException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RerouteServicePortImpl implements RerouteServicePort {

    private final RerouteService rerouteService;

    private final ParcelValidatorService parcelValidatorService;

    private final RerouteTokenValidatorService rerouteTokenValidatorService;

    @Override
    public ParcelResponse update(UpdateParcelRequest request) {

        validateParcel(request.getId());

        validateRerouteToken(request.getToken());

        return rerouteService.update(request);
    }

    @Override
    public RerouteTokenResponse findByToken(Token token) {
        return rerouteService.findByToken(token);
    }

    @Override
    public RerouteTokenResponse loadByTokenAndParcelId(Token token, ParcelId aParcelId) {
        return rerouteService.loadByTokenAndParcelId(token, aParcelId);
    }

    @Override
    public ParcelResponse loadByParcelId(java.lang.Long aLong) {
        return rerouteService.loadByParcelId(aLong);
    }

    @Override
    public RerouteResponse sendReroutingInformation(RerouteRequest rerouteRequest) {
        return rerouteService.sendReroutingInformation(rerouteRequest);
    }

    public void validateParcel(Long id) {
        final boolean parcelValidate = parcelValidatorService.validate(id);
        if (!parcelValidate) {
            throw new ParcelNotFoundException("Parcel was not found");
        }
    }

    public void validateRerouteToken(Integer token) {
        final boolean rerouteTokenValidate = rerouteTokenValidatorService.validate(token);
        if (!rerouteTokenValidate) {
            throw new RerouteTokenNotFoundException("Reroute token was not found");
        }
    }

}
