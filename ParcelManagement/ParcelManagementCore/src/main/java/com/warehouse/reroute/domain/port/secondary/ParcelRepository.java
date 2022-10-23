package com.warehouse.reroute.domain.port.secondary;

import com.warehouse.reroute.domain.model.UpdateParcelRequest;
import com.warehouse.reroute.domain.vo.ParcelResponse;

import java.util.Optional;

public interface ParcelRepository {

    Optional<ParcelResponse> update(UpdateParcelRequest request);

    ParcelResponse loadByParcelId(Long parcelId);

}
