package com.warehouse.reroute.domain.port.secondary;

import com.warehouse.reroute.domain.model.RerouteToken;
import com.warehouse.reroute.domain.model.Token;
import com.warehouse.reroute.domain.vo.ParcelId;

public interface RerouteTokenRepository {

    RerouteToken loadByTokenAndParcelId(Token token, ParcelId aParcelId);

    RerouteToken findByToken(Token token);

    Integer saveReroutingToken(Long id);

    void deleteByToken(Token token);
}
