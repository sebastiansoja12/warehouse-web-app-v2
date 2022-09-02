package com.warehouse.mvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.warehouse.dto.LoginRequest;
import com.warehouse.dto.RegisterRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = {"/application-test.properties"})
@ActiveProfiles("test")
public class AuthMvcTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    @Transactional
    void shouldRegisterUser() throws Exception {
        // given
        final RegisterRequest registerRequest = createRegisterRequest();
        final ObjectMapper mapper = new ObjectMapper();
        // when
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        final ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        final String requestJson = ow.writeValueAsString(registerRequest);
        final MvcResult login = mockMvc.perform(post("/api/users/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();
        // then
        final String token = login.getResponse().getContentAsString();
        assertThat(token).isNotNull();
    }

    @Test
    void shouldLoginAndGetContent() throws Exception {
        // given
        final LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("s-soja");
        loginRequest.setPassword("test");
        final ObjectMapper mapper = new ObjectMapper();
        // when
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        final ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        final String requestJson = ow.writeValueAsString(loginRequest);

        // then
        final MvcResult login = mockMvc.perform(post("/api/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();
        final String token = login.getResponse().getContentAsString();
        assertThat(token).isNotNull();
    }

    @Test
    @Transactional
    void shouldLoginAndGetError() throws Exception {
        // given
        final LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(null);
        loginRequest.setPassword(null);
        final ObjectMapper mapper = new ObjectMapper();
        // when
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        final ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        final String requestJson = ow.writeValueAsString(loginRequest);

        // then
        final MvcResult login = mockMvc.perform(post("/api/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(print())
                .andExpect(status().is(406))
                .andReturn();
        final String errorMessage = login.getResponse().getErrorMessage();
        assertThat(errorMessage).isEqualTo(null);
    }

    private RegisterRequest createRegisterRequest() {
        final RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setFirstName("test");
        registerRequest.setLastName("test");
        registerRequest.setUsername("test");
        registerRequest.setPassword("test");
        registerRequest.setDepotCode("KT1");
        registerRequest.setEmail("test@test.pl");

        return registerRequest;
    }
}
