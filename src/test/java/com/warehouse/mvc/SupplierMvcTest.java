package com.warehouse.mvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.warehouse.dto.LoginRequest;
import com.warehouse.dto.RegisterRequest;
import com.warehouse.dto.SupplierDto;
import com.warehouse.entity.Depot;
import com.warehouse.entity.Supplier;
import com.warehouse.entity.User;
import com.warehouse.repository.DepotRepository;
import com.warehouse.repository.SupplierRepository;
import com.warehouse.repository.UserRepository;
import com.warehouse.service.SupplierService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
public class SupplierMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private DepotRepository depotRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    void shouldReturnSupplierByCode() throws Exception {
        // given
        final Depot depot = new Depot();
        depot.setCity("Rybnik");
        depot.setStreet("Kolorowa 12");
        depot.setCountry("Polska");
        depot.setDepotCode("RB1");

        // save depot in db
        depotRepository.save(depot);

        final Supplier supplier = Supplier.builder()
                .supplierCode("SEB_KT2")
                .firstName("Sebastian")
                .lastName("Soja")
                .telephone("123")
                .depot(depot)
                .build();

        // save supplier in db
        supplierRepository.save(supplier);

        // when
        final MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(
                "/api/suppliers/" + supplier.getSupplierCode()))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();
        // then
        final Supplier supplierTest = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Supplier.class);

        assertThat(supplierTest).isNotNull();
        assertThat(supplierTest.getSupplierCode()).isEqualTo("SEB_KT2");
        assertThat(supplierTest.getFirstName()).isEqualTo("Sebastian");
        assertThat(supplierTest.getLastName()).isEqualTo("Soja");
        assertThat(supplierTest.getDepot().getDepotCode()).isEqualTo("RB1");
    }

    @Test
    @Disabled
    void shouldReturnSupplierByCommonDepotCodes() throws Exception {
        // given
        final Depot depot = new Depot();
        depot.setCity("Rybnik");
        depot.setStreet("Kolorowa 12");
        depot.setCountry("Polska");
        depot.setDepotCode("RB1");

        // save depot in db
        depotRepository.save(depot);

        final Supplier supplier = Supplier.builder()
                .supplierCode("SEB_KT2")
                .firstName("Sebastian")
                .lastName("Soja")
                .telephone("123")
                .depot(depot)
                .build();

        final User user = createUser(depot);

        // save supplier in db
        supplierRepository.save(supplier);

        // create register request because
        final RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setFirstName("Test");
        registerRequest.setLastName("Test");
        registerRequest.setUsername("test");
        registerRequest.setPassword("test");
        registerRequest.setEmail("test@test.com");
        registerRequest.setDepotCode(depot.getDepotCode());

        final LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("test");
        loginRequest.setPassword("test");

        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        final ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
        final String registerRequestJson = ow.writeValueAsString(registerRequest);
        final String loginRequestJson = ow.writeValueAsString(loginRequest);

        // when
        final MvcResult mvcRegister = mockMvc.perform(MockMvcRequestBuilders.post(
                        "/api/users/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(registerRequestJson))
                        .andDo(print())
                        .andExpect(status().is(200))
                        .andReturn();

        final MvcResult mvcLogin = mockMvc.perform(post("/api/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loginRequestJson))
                        .andDo(print())
                        .andExpect(status().is(200))
                        .andReturn();

        final MvcResult mvcDepotCode = mockMvc.perform(MockMvcRequestBuilders.get(
                        "/api/suppliers"))
                        .andDo(print())
                        .andExpect(status().is(200))
                        .andReturn();

        // then
        final Supplier supplierTest = objectMapper.readValue(mvcDepotCode.getResponse().getContentAsString(),
                Supplier.class);
        assertThat(mvcRegister).isNotNull();
        assertThat(mvcLogin.getResponse()).isNotNull();
        assertThat(mvcDepotCode).isNotNull();
        assertThat(supplierTest.getSupplierCode()).isEqualTo(user.getDepot().getDepotCode());

    }

    User createUser(Depot depot) {
       return User.builder()
                .username("test")
                .password("test")
                .email("test@test.test")
                .lastName("test")
                .firstName("test")
                .depot(depot)
                .role("admin")
                .build();
    }

}
