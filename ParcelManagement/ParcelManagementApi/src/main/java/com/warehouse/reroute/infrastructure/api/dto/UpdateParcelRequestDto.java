package com.warehouse.reroute.infrastructure.api.dto;

import lombok.*;

@Data
public class UpdateParcelRequestDto {

    ParcelIdDto parcelId;
    ParcelDto parcel;
    TokenDto token;
}
