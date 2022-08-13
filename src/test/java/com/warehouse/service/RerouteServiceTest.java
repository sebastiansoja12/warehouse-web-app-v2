package com.warehouse.service;


import com.warehouse.entity.Parcel;
import com.warehouse.entity.RerouteToken;
import com.warehouse.repository.ParcelRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class RerouteServiceTest {

    @Autowired
    private ParcelRepository parcelRepository;

    @Autowired
    private RerouteService rerouteService;

    @AfterEach
    void tearDown() {
        parcelRepository.deleteAll();
    }

    @Test
    void shouldGenerateRerouteToken() {
        // given: Parcel with random UUID
        final Parcel parcel = Parcel.builder()
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

        // and: save in db
        parcelRepository.save(parcel);

        // when
        final RerouteToken rerouteToken = rerouteService.generateReroutingToken(parcel);

        // then
        assertThat(rerouteToken).isNotNull();
    }
}
