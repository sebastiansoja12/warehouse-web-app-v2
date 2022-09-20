package com.warehouse.parcelmanagement.reroute.domain.model;


import com.warehouse.parcelmanagement.reroute.domain.enumeration.ParcelType;
import com.warehouse.parcelmanagement.reroute.domain.vo.Recipient;
import com.warehouse.parcelmanagement.reroute.domain.vo.Sender;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Parcel {
     Sender sender;
     Recipient recipient;
     ParcelType parcelType;
}
