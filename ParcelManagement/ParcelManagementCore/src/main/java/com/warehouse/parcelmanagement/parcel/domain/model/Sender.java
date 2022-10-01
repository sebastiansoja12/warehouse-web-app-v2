package com.warehouse.parcelmanagement.parcel.domain.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Sender {

    String firstName;
    String lastName;
    String email;
    String telephoneNumber;
    String city;
    String postalCode;
    String street;
}
