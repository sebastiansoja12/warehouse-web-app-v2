package com.warehouse.parcelmanagement.reroute.infrastructure.api.dto;

import lombok.Value;

@Value
public class SenderDto {
    String firstName;
    String lastName;
    String email;
    String telephoneNumber;
    String city;
    String postalCode;
    String street;
}
