package com.warehouse.service;


import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.warehouse.configuration.ParcelServiceIntegrationTestConfiguration;
import com.warehouse.model.Parcel;
import com.warehouse.repository.ParcelRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ParcelServiceIntegrationTestConfiguration.class)
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        TransactionDbUnitTestExecutionListener.class
})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
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
        // AND: List of parcels
        final List<Parcel> parcels = List.of(parcel);

        // WHEN: Parcel is saved
        final Optional<Parcel> parcelToFind = parcelRepository
                .findById(UUID.fromString("b9c70ce3-025c-477d-8d27-19260433b84f"));
        // THEN
        assertTrue(parcelToFind.isPresent());
        assertThat(parcelToFind.get().getId().equals(UUID.fromString("b9c70ce3-025c-477d-8d27-19260433b84f")));
    }
}
