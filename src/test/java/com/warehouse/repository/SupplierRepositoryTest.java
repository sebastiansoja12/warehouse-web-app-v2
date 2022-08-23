package com.warehouse.repository;

import com.warehouse.dto.SupplierDto;
import com.warehouse.entity.Depot;
import com.warehouse.entity.Supplier;
import com.warehouse.entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class SupplierRepositoryTest {

    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private DepotRepository depotRepository;

    @AfterEach
    void tearDown() {
        supplierRepository.deleteAll();
    }

    @Test
    void shouldSaveSupplier() {
        // given
        final Depot depot = Depot.builder()
                .city("Test")
                .street("Test street")
                .depotCode("TS1")
                .country("Test")
                .build();

        // and: save depot in db
        depotRepository.save(depot);

        final SupplierDto supplierDto = new SupplierDto();
        supplierDto.setSupplierCode("TS1");
        supplierDto.setDepotCode("TS1");
        supplierDto.setFirstName("Test");
        supplierDto.setLastName("Test");
        supplierDto.setTelephone("123");

        final Supplier supplier = Supplier.builder()
                .supplierCode(supplierDto.getSupplierCode())
                .firstName(supplierDto.getFirstName())
                .lastName(supplierDto.getLastName())
                .telephone(supplierDto.getTelephone())
                .depot(depot)
                .build();
        // when
        supplierRepository.save(supplier);
        final Long supplierId = supplier.getId();

        // then
        assertThat(supplier.getId()).isEqualTo(supplierId);
    }

    @Test
    void shouldFindBySupplierCode() {
        // given
        final Depot depot = Depot.builder()
                .city("Test")
                .street("Test street")
                .depotCode("TS1")
                .country("Test")
                .build();

        // and: save depot in db
        depotRepository.save(depot);

        final SupplierDto supplierDto = new SupplierDto();
        supplierDto.setSupplierCode("TS1");
        supplierDto.setDepotCode("TS1");
        supplierDto.setFirstName("Test");
        supplierDto.setLastName("Test");
        supplierDto.setTelephone("123");

        final Supplier supplier = Supplier.builder()
                .supplierCode(supplierDto.getSupplierCode())
                .firstName(supplierDto.getFirstName())
                .lastName(supplierDto.getLastName())
                .telephone(supplierDto.getTelephone())
                .depot(depot)
                .build();

        supplierRepository.save(supplier);

        // when
        final Supplier expected = supplierRepository.findBySupplierCode(supplier.getSupplierCode());

        // then
        assertThat(expected).isNotNull();
        assertThat(expected.getSupplierCode()).isEqualTo(supplier.getSupplierCode());
    }

    @Test
    void shouldReturnSuppliersWithTheSameDepotCodeLikeUser() {
        // given
        final List<Supplier> suppliers = fakeSuppliers();

        // and: save suppliers in db
        supplierRepository.saveAll(suppliers);

        // and: user
        final User user = User.builder()
                .depot(fakeDepots().get(0))
                .firstName("Test")
                .lastName("Test")
                .email("test@test.pl")
                .username("Test")
                .password("Test")
                .role("admin")
                .build();

        // when with method used in SupplierService
        final List<Supplier> suppliersWithDepotCodeKT1 = findAllSupplierByCommonDepotCodes(user);

        // then
        assertThat(suppliersWithDepotCodeKT1).isNotNull();
        assertThat(suppliersWithDepotCodeKT1.size()).isEqualTo(1);
        assertThat(suppliersWithDepotCodeKT1.get(0).getDepot().getDepotCode()).isEqualTo("KT1");
    }

    List<Supplier> fakeSuppliers() {
        // list of depots
        final List<Depot> depots = fakeDepots();

        // list of suppliers
        final List<Supplier> suppliers = new ArrayList<>();

        // save depots in db
        depotRepository.saveAll(depots);

        final Supplier sup_kt1 = Supplier.builder()
                .telephone("123")
                .supplierCode("TS_KT1")
                .lastName("Test")
                .firstName("Test")
                .depot(depots.get(0))
                .build();

        final Supplier sup_poz = Supplier.builder()
                .telephone("123")
                .supplierCode("TS_KT1")
                .lastName("Test")
                .firstName("Test")
                .depot(depots.get(1))
                .build();

        final Supplier sup_szz = Supplier.builder()
                .telephone("123")
                .supplierCode("TS_KT1")
                .lastName("Test")
                .firstName("Test")
                .depot(depots.get(2))
                .build();

        // add suppliers in list
        suppliers.add(sup_kt1);
        suppliers.add(sup_poz);
        suppliers.add(sup_szz);

        return suppliers;
    }

    List<Depot> fakeDepots() {
        // list of depots
        final List<Depot> depots = new ArrayList<>();

        // create depots kt1, poz, szz
        final Depot kt1 = Depot.builder()
                .street("Test")
                .city("Test")
                .country("Test")
                .depotCode("KT1")
                .build();

        final Depot poz = Depot.builder()
                .street("Test")
                .city("Test")
                .country("Test")
                .depotCode("POZ")
                .build();

        final Depot szz = Depot.builder()
                .street("Test")
                .city("Test")
                .country("Test")
                .depotCode("SZZ")
                .build();

        // add all depots to list
        depots.add(kt1);
        depots.add(poz);
        depots.add(szz);

        return depots;
    }

    public List<Supplier> findAllSupplierByCommonDepotCodes(User user) {
        return supplierRepository
                .findAll()
                .stream()
                .filter(s -> s.getDepot().getDepotCode()
                        .equals(user
                                .getDepot()
                                .getDepotCode()))
                .collect(Collectors.toList());
    }
}
