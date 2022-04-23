package com.warehouse.warehouse.dto;

import lombok.Data;

@Data
public class ParcelDto {

    private Long customerId;

    private Long recipientId;

    private double price;

    private boolean isCustom;

    private ParcelTypeDto parcelType;
}
