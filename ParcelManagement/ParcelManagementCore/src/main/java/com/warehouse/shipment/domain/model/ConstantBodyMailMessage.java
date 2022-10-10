package com.warehouse.shipment.domain.model;

import lombok.Data;

@Data
public class ConstantBodyMailMessage {

    String message;

    Long parcelId;

    String url;

    String labelUrl;

    String parcelManagementUrl;

    String city;

    String street;

    String paymentUrl;


    public ConstantBodyMailMessage(Parcel parcel, String paymentUrl) {
        this.labelUrl = "http://localhost:8080/api/parcels/ " + parcel.getId() + "/label";
        this.parcelManagementUrl = "http://localhost:4200/shipment/client/management/" + parcel.getId();
        this.message = "Docelowa destynacja paczki to: " +
                parcel.getRecipient().getCity() + parcel.getRecipient().getStreet() +
                "\nKod państwa paczki to: " + parcel.getId() +
                "\nAby pobrać etykietę prosimy wejść w " + getLabelUrl() +
                "\nAby opłacić przesyłkę prosimy wejść w : " + paymentUrl +
                "\nAby zarządzać przesyłką prosimy wejść w: " + this.parcelManagementUrl;

    }

}
