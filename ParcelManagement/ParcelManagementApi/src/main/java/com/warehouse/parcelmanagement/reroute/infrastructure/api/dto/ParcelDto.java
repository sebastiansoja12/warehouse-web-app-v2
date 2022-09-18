package com.warehouse.parcelmanagement.reroute.infrastructure.api.dto;

import com.warehouse.parcelmanagement.parcel.infrastructure.api.dto.ParcelId;
import com.warehouse.parcelmanagement.parcel.infrastructure.api.dto.ParcelTypeDto;
import lombok.Data;
import lombok.Value;

@Value
public class ParcelDto {

    SenderDto sender;
    RecipientDto recipient;
    ParcelTypeDto parcelType;
}
