package com.warehouse.service;


import com.warehouse.dto.SupplierDto;
import com.warehouse.entity.Depot;
import com.warehouse.entity.Supplier;
import com.warehouse.exceptions.DepotNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("dev")
public class SupplierServiceTest {

    @Autowired
    private SupplierService supplierService;

    @Test
    @Transactional
    void shouldFindBySupplierCode() {
        // given
        // create supplier
        final SupplierDto supplierDto = new SupplierDto();
        supplierDto.setSupplierCode("TEST");
        supplierDto.setDepotCode("KT1");
        supplierDto.setFirstName("Test");
        supplierDto.setLastName("Test");
        supplierDto.setTelephone("123");

        final String supplierCode = "TEST";
        // when
        supplierService.save(supplierDto);
        final Supplier supplier = supplierService.findBySupplierCode(supplierCode);
        // then
        assertThat(supplier).isNotNull();
    }

    @Test
    @Transactional
    void shouldNotFindBySupplierCode() {
        // given
        // create supplier
        final SupplierDto supplierDto = new SupplierDto();
        supplierDto.setSupplierCode("TEST");
        supplierDto.setDepotCode("KT1");
        supplierDto.setFirstName("Test");
        supplierDto.setLastName("Test");
        supplierDto.setTelephone("123");

        final String supplierCode = "TEST2";
        // when
        supplierService.save(supplierDto);
        final Supplier supplier = supplierService.findBySupplierCode(supplierCode);
        // then
        assertThat(supplier).isNull();
    }

    @Test
    @Transactional
    void shouldDeleteBySupplierCode() {
        // given
        // create supplier
        final SupplierDto supplierDto = new SupplierDto();
        supplierDto.setSupplierCode("TO_DELETE");
        supplierDto.setDepotCode("KT1");
        supplierDto.setFirstName("Test");
        supplierDto.setLastName("Test");
        supplierDto.setTelephone("123");

        final String supplierCode = "TEST2";
        // when
        supplierService.save(supplierDto);
        supplierService.delete(supplierCode);
        // then
        final Supplier supplier = supplierService.findBySupplierCode(supplierCode);
        // assert that supplier was deleted
        assertThat(supplier).isNull();
    }

    @Test
    @Transactional
    void shouldReturnDepotBySupplierDepotCode() {
        // given
        // create supplier
        final SupplierDto supplierDto = new SupplierDto();
        supplierDto.setSupplierCode("SEB_KT");
        supplierDto.setDepotCode("KT1");
        supplierDto.setFirstName("Test");
        supplierDto.setLastName("Test");
        supplierDto.setTelephone("123");

        // when
        supplierService.save(supplierDto);
        final Depot depot = supplierService.getDepotByCode(supplierDto);
        // then
        assertThat(depot).isNotNull();
        assertThat(depot.getDepotCode()).isEqualTo(supplierDto.getDepotCode());
    }

    @Test
    @Transactional
    void shouldNotSaveSupplier() {
        // given
        // create supplier
        final SupplierDto supplierDto = new SupplierDto();
        supplierDto.setSupplierCode("SEB_KT");
        supplierDto.setDepotCode("TST");
        supplierDto.setFirstName("Test");
        supplierDto.setLastName("Test");
        supplierDto.setTelephone("123");

        // when: should not save because given depot does not exist
        final Executable executable = () -> supplierService.save(supplierDto);
        final DepotNotFoundException exception = assertThrows(DepotNotFoundException.class, executable);

        // then
        assertThat(exception).isNotNull();
        assertThat(exception.getMessage()).isEqualTo("Given depot doesnt exist!");
    }

    @Test
    void shouldNotReturnDepotBySupplierDepotCode() {
        // given
        // create supplier
        final SupplierDto supplierDto = new SupplierDto();
        supplierDto.setSupplierCode("SEB_KT");
        supplierDto.setDepotCode("TST");
        supplierDto.setFirstName("Test");
        supplierDto.setLastName("Test");
        supplierDto.setTelephone("123");

        // when: should not save because given depot does not exist
        final DepotNotFoundException exception = assertThrows(DepotNotFoundException.class, () -> {
            supplierService.getDepotByCode(supplierDto);
        });

        // then
        assertThat(exception).isNotNull();
        assertThat(exception.getMessage()).isEqualTo("Given depot doesnt exist!");
    }
}
