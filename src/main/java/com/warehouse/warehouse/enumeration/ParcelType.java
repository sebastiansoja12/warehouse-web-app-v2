package com.warehouse.warehouse.enumeration;

import lombok.AllArgsConstructor;

public enum ParcelType {
    TINY("5cmx5cmx5cm"),
    SMALL("10cmx10cmx10cm"),
    MEDIUM("30cmx30cmx30cm"),
    AVERAGE("50cmx50cmx50cm"),
    BIG("80cmx80cmx80cm"),
    CUSTOM("XcmXcmXcm");

    private final String parcelSize;

    ParcelType(String size) {
        this.parcelSize = size;
    }

    public String getSize() {
        return parcelSize;
    }
}
