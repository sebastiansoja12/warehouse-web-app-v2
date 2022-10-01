package com.warehouse.inmemorydatabase;

import com.warehouse.entity.Parcel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class InMemoryParcelDatabase {

    private final List<Parcel> parcelList = new ArrayList<>();


    public List<Parcel> createParcel() {
         final Parcel parcel = Parcel.builder()
                .id(100001L)
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

}
