package com.warehouse.reroute.domain.vo;

import com.warehouse.reroute.domain.enumeration.ParcelType;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ParcelResponse {
    ParcelId parcelId;
    Sender sender;
    Recipient recipient;
    ParcelType parcelType;
}
