package com.warehouse.parcelmanagement.reroute.domain.port.secondary;

import com.warehouse.parcelmanagement.reroute.domain.model.UpdateParcelRequest;
import com.warehouse.parcelmanagement.reroute.domain.vo.ParcelId;
import com.warehouse.parcelmanagement.reroute.domain.vo.ParcelResponse;

import java.util.Optional;

public interface ParcelRepository {

    ParcelResponse update(UpdateParcelRequest request);

    Optional<ParcelResponse> loadByParcelId(Long parcelId);

}
