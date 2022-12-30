package com.warehouse.supplier;

import com.warehouse.supplier.configuration.SupplierTestConfiguration;
import com.warehouse.supplier.domain.model.Supplier;
import com.warehouse.supplier.domain.port.secondary.SupplierServicePort;
import com.warehouse.supplier.dto.SupplierDto;
import com.warehouse.supplier.infrastructure.adapter.secondary.SupplierReadRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ContextConfiguration(classes = SupplierTestConfiguration.class)
public class SupplierServicePortTest {

    @Autowired
    private SupplierServicePort servicePort;

    @Autowired
    private SupplierReadRepository supplierReadRepository;


    @Test
    void shouldReturnAllSuppliers() {
        // given
        final Supplier supplier = createSupplier();
        servicePort.create(supplier);
        // when
        final List<Supplier> suppliers = servicePort.findAll();
        // then
        assertThat(suppliers.size()).isGreaterThan(0);
    }

    @Test
    void shouldReturnBySupplierCode() {
        // given
        final Supplier supplier = createSupplier();
        servicePort.create(supplier);
        final String supplierCode = "test";
        // when
        final Supplier supplierByCode = servicePort.findSupplierByCode(supplierCode);
        // then
        assertThat(supplierByCode).isNotNull();
    }


    private Supplier createSupplier() {
        final Supplier supplier = new Supplier();
        supplier.setFirstName("test");
        supplier.setLastName("test");
        supplier.setSupplierCode("test");
        supplier.setTelephone("123");
        return supplier;
    }


}
