package com.warehouse.parcelmanagement.reroute.infrastructure.api.dto;

import lombok.Value;

@Value
public class UpdateParcelRequestDto {

    ParcelIdDto parcelId;
    ParcelDto parcel;
    TokenDto token;
}
