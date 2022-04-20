package com.warehouse.warehouse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class CustomerDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String telephoneNumber;

    private String city;

    private String street;

    private String postalCode;

    private String emailAddress;
}
