package com.warehouse.inmemorydatabase;

import com.warehouse.model.Parcel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class InMemoryParcelDatabase {

    private final List<Parcel> parcelList = new ArrayList<>();


    public List<Parcel> createParcel() {
         final Parcel parcel = Parcel.builder()
                .id(UUID.fromString("b9c70ce3-025c-477d-8d27-19260433b84f"))
                .firstName("Test")
                .lastName("Test")
                .senderTelephone("123456789")
                .senderEmail("test@test.test")
                .recipientFirstName("Test")
                .recipientLastName("Test")
                .recipientEmail("test@test.test")
                .recipientTelephone("123456789")
                .recipientCity("Test")
                .recipientStreet("Test")
                .recipientPostalCode("00-000")
                .build();
        parcelList.add(parcel);
        return parcelList;

    }

    public Optional<Parcel> findParcelByUUID(String UUID) {
        return parcelList.stream()
                .filter(p -> p.getId().toString().equals(UUID)).findAny();
    }
}
