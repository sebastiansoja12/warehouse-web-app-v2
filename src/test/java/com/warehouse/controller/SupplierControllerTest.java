package com.warehouse.controller;


import com.warehouse.dto.AuthenticationResponse;
import com.warehouse.dto.LoginRequest;
import com.warehouse.dto.RegisterRequest;
import com.warehouse.entity.Supplier;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@ActiveProfiles("test")
public class SupplierControllerTest {

    @Autowired
    private SupplierController supplierController;

    @Autowired
    private AuthController authController;

    @Test
    void shouldReturnAllSupplierByCommonDepotCodes() {
        // exists only ONE supplier with KT1 depot code
        // given: create login request because logged in user is needed
        final LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("s-soja");
        loginRequest.setPassword("test");
        // when
        // and: user is logged in
        final AuthenticationResponse authenticationResponse = authController.login(loginRequest);
        final List<Supplier> suppliers = supplierController.getAll();
        // then
        assertThat(suppliers.size()).isEqualTo(1);
        assertThat(suppliers.get(0).getDepot().getDepotCode()).isEqualTo("KT1");
    }

    @Test
    @Transactional
    void shouldReturnNone() {
        // does not exist any supplier with given depot code
        // given: create register request
        final RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setFirstName("Test");
        registerRequest.setLastName("Test");
        registerRequest.setUsername("test");
        registerRequest.setPassword("test");
        registerRequest.setEmail("test@test.com");
        registerRequest.setDepotCode("LW1");

        final LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("test");
        loginRequest.setPassword("test");

        // when: user is registered first
        authController.signup(registerRequest);
        // and: user is logged in
        authController.login(loginRequest);
        final List<Supplier> suppliers = supplierController.getAll();
        // then
        assertThat(suppliers.size()).isEqualTo(0);
    }

    @Test
    void shouldFindSupplierBySupplierCode() {
        // given
        final String supplierCode = "SEB_KT1";

        // when: find supplier
        final Supplier supplier = supplierController.findSupplierBySupplierCode(supplierCode);

        // then
        assertThat(supplier).isNotNull();
        assertThat(supplier.getSupplierCode()).isEqualTo(supplierCode);
    }
}
