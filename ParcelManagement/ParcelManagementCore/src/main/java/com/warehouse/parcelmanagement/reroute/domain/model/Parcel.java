package com.warehouse.parcelmanagement.reroute.domain.model;

import com.warehouse.parcelmanagement.parcel.domain.model.Recipient;
import com.warehouse.parcelmanagement.parcel.domain.model.Sender;

public class Parcel {

    private Sender sender;
    private Recipient recipient;
    private Country country;
    private String street;
    private String postalCode;
}
