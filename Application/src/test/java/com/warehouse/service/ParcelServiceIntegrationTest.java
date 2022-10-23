package com.warehouse.service;


import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.warehouse.entity.Parcel;
import com.warehouse.repository.ParcelRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        TransactionDbUnitTestExecutionListener.class
})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Disabled
public class ParcelServiceIntegrationTest {

    @Autowired
    private ParcelRepository parcelRepository;

    @LocalServerPort
    private int port;


    @DatabaseSetup("/src/test/resources/data001/parcel_repository.xml")
    @Test
    public void shouldFindInDatabase() {
        // GIVEN: Parcel with random UUID
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
        // AND: List of parcels
        final List<Parcel> parcels = List.of(parcel);

        // WHEN: Parcel is saved
        final Optional<Parcel> parcelToFind = parcelRepository
                .findById(123456L);
        // THEN
        assertTrue(parcelToFind.isPresent());
    }
}
