package com.warehouse.repository;

import com.warehouse.entity.Parcel;
import com.warehouse.enumeration.ParcelType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class ParcelRepositoryTest {

    @Autowired
    private ParcelRepository parcelRepository;

    @AfterEach
    void tearDown() {
        parcelRepository.deleteAll();
    }

    @Test
    void shouldSaveParcel() {
        // given: Parcel with random UUID
        final Parcel parcel = Parcel.builder()
                .firstName("Test")
                .lastName("Test")
                .senderTelephone("123456789")
                .senderEmail("test@test.test")
                .senderCity("Test")
                .senderStreet("Test")
                .senderPostalCode("00-000")
                .recipientFirstName("Test")
                .recipientLastName("Test")
                .recipientEmail("test@test.test")
                .recipientTelephone("123456789")
                .recipientCity("Test")
                .recipientStreet("Test")
                .recipientPostalCode("00-000")
                .parcelType(ParcelType.AVERAGE)
                .build();
        // when
        parcelRepository.save(parcel);
        // then
        assertThat(parcel.getId()).isNotNull();
        // and: id is instance of UUID class
        assertThat(parcel.getId()).isInstanceOf(Long.class);
    }

    @Test
    void shouldFindById() {
        // given: Parcel with random UUID
        final Parcel parcel = Parcel.builder()
                .firstName("Test")
                .lastName("Test")
                .senderTelephone("123456789")
                .senderEmail("test@test.test")
                .senderCity("Test")
                .senderStreet("Test")
                .senderPostalCode("00-000")
                .recipientFirstName("Test")
                .recipientLastName("Test")
                .recipientEmail("test@test.test")
                .recipientTelephone("123456789")
                .recipientCity("Test")
                .recipientStreet("Test")
                .recipientPostalCode("00-000")
                .parcelType(ParcelType.AVERAGE)
                .build();

        parcelRepository.save(parcel);

        final Long id = parcel.getId();

        // when
        final boolean exists = parcelRepository
                .existsById(id);

        // then
        assertTrue(exists);
    }

    @Test
    void shouldGetById() {
        // given: Parcel with random UUID
        final Parcel parcel = Parcel.builder()
                .firstName("Test")
                .lastName("Test")
                .senderTelephone("123456789")
                .senderEmail("test@test.test")
                .senderCity("Test")
                .senderStreet("Test")
                .senderPostalCode("00-000")
                .recipientFirstName("Test")
                .recipientLastName("Test")
                .recipientEmail("test@test.test")
                .recipientTelephone("123456789")
                .recipientCity("Test")
                .recipientStreet("Test")
                .recipientPostalCode("00-000")
                .parcelType(ParcelType.AVERAGE)
                .build();

        parcelRepository.save(parcel);

        final Long uuid = parcel.getId();

        // when
        final Parcel expected = parcelRepository.getById(uuid);

        // then
        assertThat(expected).isNotNull();
        // and: id is instance of UUID class
        assertThat(expected.getId()).isInstanceOf(Long.class);
    }
}
