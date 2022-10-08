package com.warehouse.reroute.domain.port.secondary;

import com.warehouse.reroute.domain.model.UpdateParcelRequest;
import com.warehouse.reroute.domain.vo.ParcelResponse;

public interface ParcelPort {

    ParcelResponse update(UpdateParcelRequest request);

    ParcelResponse loadByParcelId(Long parcelId);

}
