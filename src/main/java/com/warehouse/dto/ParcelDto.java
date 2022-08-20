package com.warehouse.dto;

import lombok.Data;

@Data
public class ParcelDto {

    private String firstName;

    private String lastName;

    private String senderTelephone;

    private String senderEmail;

    private String recipientEmail;

    private String recipientTelephone;

    private String recipientFirstName;

    private String recipientLastName;

    private String recipientCity;

    private String recipientStreet;

    private String recipientPostalCode;

    private ParcelTypeDto parcelType;

    private double price;
}
