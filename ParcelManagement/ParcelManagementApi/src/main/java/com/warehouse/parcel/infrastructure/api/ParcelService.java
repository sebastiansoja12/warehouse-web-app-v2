package com.warehouse.parcel.infrastructure.api;

import com.warehouse.parcel.infrastructure.api.dto.ParcelDto;

public interface ParcelService {

    String save(ParcelDto parcel);

    ParcelDto findParcelById(Long id);
}
