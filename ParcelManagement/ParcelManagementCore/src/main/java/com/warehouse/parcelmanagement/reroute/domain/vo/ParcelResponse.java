package com.warehouse.parcelmanagement.reroute.domain.vo;

import com.warehouse.parcelmanagement.reroute.domain.enumeration.ParcelType;
import lombok.Value;

@Value
public class ParcelResponse {
    ParcelId parcelId;
    Sender sender;
    Recipient recipient;
    ParcelType parcelType;
}
