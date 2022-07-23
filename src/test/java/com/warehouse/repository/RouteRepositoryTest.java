package com.warehouse.repository;

import com.warehouse.entity.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DataJpaTest
public class RouteRepositoryTest {

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private ParcelRepository parcelRepository;

    @AfterEach
    void tearDown() {
    }

    @Test
    void shouldSaveRoute() {
        // given
        final Depot depot = createDepot();
        depot.setId(1L);

        final Parcel parcel = createParcel();
        // and: set parcel id
        parcel.setId(UUID.fromString("b9c70ce3-025c-477d-8d27-19260433b84f"));


        final User user = createUser();
        // and: set user id
        user.setId(1);

        final Supplier supplier = createSupplier();
        // and: set supplier id
        supplier.setId(1L);

        final Route route = Route.builder()
                .parcel(parcel)
                .supplier(supplier)
                .depot(depot)
                .user(user)
                .created(LocalDateTime.now(ZoneId.of(String.valueOf(ZoneId.systemDefault()))))
                .build();

        // when
        routeRepository.save(route);
        // then
        assertThat(route.getId()).isNotNull();
        // and: id is instance of UUID class
        assertThat(route.getId()).isInstanceOf(UUID.class);

    }

    @Test
    void shouldFindByUsername() {
        // given

        final User user = createUser();
        // and: set user id
        user.setId(1);
        // and: set depot id
        user.getDepot().setId(1L);


        final Route route = Route.builder()
                .user(user)
                .created(LocalDateTime.now(ZoneId.of(String.valueOf(ZoneId.systemDefault()))))
                .build();

        routeRepository.save(route);

        // when
        final String username = user.getUsername();
        // then
        assertThat(route).isNotNull();
        assertThat(route.getUser().getUsername()).isEqualTo(username);
    }

    @Test
    void shouldFindById() {
        // given
        final Parcel parcel = createParcel();

        // and: save parcel in db
        parcelRepository.save(parcel);

        final Route route = Route.builder()
                .parcel(parcel)
                .created(LocalDateTime.now(ZoneId.of(String.valueOf(ZoneId.systemDefault()))))
                .build();

        routeRepository.save(route);
        final UUID id = parcel.getId();

        // when
        final List<Route> routes = routeRepository.findByParcelId(id);

        // then
        assertThat(routes).isNotNull();
        assertThat(routes.get(0).getParcel().getId()).isEqualTo(id);
    }

    @Test
    void shouldDeleteByParcelIdAndDepotCode() {
        // given
        final Parcel parcel = createParcel();
        // and: set parcel id
        parcelRepository.save(parcel);

        final Route route = Route.builder()
                .parcel(parcel)
                .created(LocalDateTime.now(ZoneId.of(String.valueOf(ZoneId.systemDefault()))))
                .build();

        routeRepository.save(route);

        // when: delete by parcel id and depot code
        routeRepository.deleteByParcelIdAndDepot_DepotCode(parcel.getId(), null);
        final boolean exists = routeRepository.existsById(route.getId());
        // then
        assertFalse(exists);

    }

    Depot createDepot() {
        return Depot.builder()
                .city("Test")
                .street("Test street")
                .depotCode("TS1")
                .country("Test")
                .build();
    }

    Parcel createParcel() {
        return Parcel.builder()
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
    }

    User createUser() {
        return User.builder()
                .depot(createDepot())
                .username("Test")
                .firstName("Test")
                .lastName("Test")
                .email("test@test.test")
                .password("test")
                .build();
    }

    Supplier createSupplier() {
        return Supplier.builder()
                .depot(createDepot())
                .firstName("Test")
                .lastName("Test")
                .supplierCode("TS1")
                .telephone("123")
                .build();
    }
}
