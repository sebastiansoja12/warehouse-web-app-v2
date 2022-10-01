package com.warehouse.parcelmanagement.reroute.infrastructure.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

@Data
@AllArgsConstructor
public class ParcelResponseDto {
    ParcelIdDto parcelId;
    SenderDto sender;
    RecipientDto recipient;
    ParcelTypeDto parcelType;
}
