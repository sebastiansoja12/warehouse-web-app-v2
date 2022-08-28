package com.warehouse.parcelmanagement.parcel.infrastructure.api;

import com.warehouse.parcelmanagement.parcel.infrastructure.api.dto.ParcelDto;

public interface ParcelService {

    String save(ParcelDto parcel);
}
