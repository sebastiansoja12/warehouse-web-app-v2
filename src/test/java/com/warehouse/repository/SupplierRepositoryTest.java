package com.warehouse.repository;

import com.warehouse.dto.SupplierDto;
import com.warehouse.entity.Depot;
import com.warehouse.entity.Supplier;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

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

        SupplierDto supplierDto = new SupplierDto();
        supplierDto.setSupplierCode("TS1");
        supplierDto.setDepotCode("TS1");
        supplierDto.setFirstName("Test");
        supplierDto.setLastName("Test");
        supplierDto.setTelephone("123");

        Supplier supplier = Supplier.builder()
                .supplierCode(supplierDto.getSupplierCode())
                .firstName(supplierDto.getFirstName())
                .lastName(supplierDto.getLastName())
                .telephone(supplierDto.getTelephone())
                .depot(depot)
                .build();

        // when
        supplierRepository.save(supplier);

        // then
        assertThat(supplier.getId()).isEqualTo(2L);
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

        SupplierDto supplierDto = new SupplierDto();
        supplierDto.setSupplierCode("TS1");
        supplierDto.setDepotCode("TS1");
        supplierDto.setFirstName("Test");
        supplierDto.setLastName("Test");
        supplierDto.setTelephone("123");

        Supplier supplier = Supplier.builder()
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
}
