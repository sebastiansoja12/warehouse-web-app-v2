package com.warehouse.reroute.infrastructure.adapter.secondary;

import com.warehouse.reroute.domain.model.UpdateParcelRequest;
import com.warehouse.reroute.domain.port.secondary.ParcelPort;
import com.warehouse.reroute.domain.port.secondary.ParcelRepository;
import com.warehouse.reroute.domain.vo.ParcelResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ParcelAdapter implements ParcelPort {

    private final ParcelRepository parcelRepository;


    @Override
    public ParcelResponse loadByParcelId(Long parcelId) {
        return parcelRepository.loadByParcelId(parcelId);
    }

    @Override
    public ParcelResponse update(UpdateParcelRequest request) {
        return parcelRepository.update(request).get();
    }
}
