package com.warehouse.supplier;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.warehouse.supplier.configuration.SupplierTestConfiguration;
import com.warehouse.supplier.infrastructure.adapter.secondary.SupplierReadRepository;
import com.warehouse.supplier.infrastructure.adapter.secondary.entity.SupplierEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ContextConfiguration(classes = SupplierTestConfiguration.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SupplierReadRepositoryTest {

    @Autowired
    private SupplierReadRepository repository;

    @Test
    @DatabaseSetup("/dataset/supplier.xml")
    void shouldReturnSuppliersByDepotCode() {
        // given
        final String depotCode = "TST";
        // when
        final List<SupplierEntity> suppliers = repository.findByDepot_DepotCode(depotCode);
        // then
        assertAll(
                () -> assertThat(suppliers.size()).isEqualTo(1L),
                () -> assertThat(suppliers.get(0).getDepot().getDepotCode()).isEqualTo(depotCode)
        );
    }

    @Test
    @DatabaseSetup("/dataset/supplier.xml")
    void shouldNotReturnSuppliersByDepotCode() {
        // given
        final String depotCode = "test";
        // when
        final List<SupplierEntity> suppliers = repository.findByDepot_DepotCode(depotCode);
        // then
        assertThat(suppliers.size()).isEqualTo(0L);
    }

    @Test
    @DatabaseSetup("/dataset/supplier.xml")
    void shouldReturnSupplierByCode() {
        // given
        final String supplierCode = "abcdef";
        // when
        final Optional<SupplierEntity> supplier = repository.findBySupplierCode(supplierCode);
        // then
        assertAll(
                () -> assertTrue(supplier.isPresent()),
                () -> assertThat(supplier.get().getSupplierCode()).isEqualTo(supplierCode)
        );
    }

    @Test
    @DatabaseSetup("/dataset/supplier.xml")
    void shouldNotReturnSupplierByCode() {
        // given
        final String supplierCode = "fedcba";
        // when
        final Optional<SupplierEntity> supplier = repository.findBySupplierCode(supplierCode);
        // then
        assertFalse(supplier.isPresent());
    }
}
