package com.warehouse.parcelmanagement.reroute.domain.vo;

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
