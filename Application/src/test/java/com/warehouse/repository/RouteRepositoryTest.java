package com.warehouse.repository;

import com.warehouse.entity.*;
import com.warehouse.enumeration.ParcelType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private DepotRepository depotRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    void shouldSaveRoute() {
        // given
        final Depot depot = createDepot();
        depot.setId(1L);

        final Parcel parcel = createParcel();
        // and: set shipment id
        parcel.setId(1000001L);


        final User user = createUser();
        // and: set user id
        userRepository.save(user);

        final Supplier supplier = createSupplier();
        // and: set supplier id
        supplierRepository.save(supplier);

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
    @Transactional
    void shouldFindByUsername() {
        // given
        final Parcel parcel = createParcel();

        final Supplier supplier = createSupplier();

        final Depot depot = createDepot();

        final User user = createUser();

        // and: save depot in db
        depotRepository.save(depot);

        // and: set depot to supplier
        supplier.setDepot(depot);

        // and: save shipment in db
        parcelRepository.save(parcel);

        // and: save supplier in db
        supplierRepository.save(supplier);

        // and: set depot id
        user.setDepot(depot);

        // and: save user in db
        userRepository.save(user);


        final Route route = Route.builder()
                .user(user)
                .created(LocalDateTime.now(ZoneId.of(String.valueOf(ZoneId.systemDefault()))))
                .supplier(supplier)
                .depot(depot)
                .parcel(parcel)
                .build();

        routeRepository.save(route);

        // when
        final String username = user.getUsername();
        // then
        assertThat(route).isNotNull();
        assertThat(route.getUser().getUsername()).isEqualTo(username);
    }

    @Test
    @Transactional
    void shouldFindById() {
        // given
        final Parcel parcel = createParcel();

        final Supplier supplier = createSupplier();

        final Depot depot = createDepot();

        // and: save depot in db
        depotRepository.save(depot);

        // and: set depot to supplier
        supplier.setDepot(depot);

        // and: save shipment in db
        parcelRepository.save(parcel);

        // and: save supplier in db
        supplierRepository.save(supplier);


        final Route route = Route.builder()
                .parcel(parcel)
                .created(LocalDateTime.now(ZoneId.of(String.valueOf(ZoneId.systemDefault()))))
                .supplier(supplier)
                .depot(depot)
                .build();

        routeRepository.save(route);
        final Long id = parcel.getId();

        // when
        final List<Route> routes = routeRepository.findByParcelId(id);

        // then
        assertThat(routes).isNotNull();
        assertThat(routes.get(0).getParcel().getId()).isEqualTo(id);
    }

    @Test
    @Transactional
    void shouldDeleteByParcelIdAndDepotCode() {
        // given
        final Parcel parcel = createParcel();

        final Supplier supplier = createSupplier();

        // and: save shipment in db
        parcelRepository.save(parcel);

        // and: save supplier in db
        supplierRepository.save(supplier);

        final Route route = Route.builder()
                .parcel(parcel)
                .created(LocalDateTime.now(ZoneId.of(String.valueOf(ZoneId.systemDefault()))))
                .supplier(supplier)
                .build();

        routeRepository.save(route);

        // when: delete by shipment id and depot code
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
        return  Parcel.builder()
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
    }

    User createUser() {
        return User.builder()
                .username("Test")
                .firstName("Test")
                .lastName("Test")
                .email("test@test.test")
                .password("test")
                .build();
    }

    Supplier createSupplier() {
        return Supplier.builder()
                .firstName("Test")
                .lastName("Test")
                .supplierCode("TS1")
                .telephone("123")
                .build();
    }
}
