package com.warehouse.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.warehouse.dto.LoginRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

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
        final MvcResult login = mockMvc.perform(post("/api/users/login").contentType(MediaType.APPLICATION_JSON)
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
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(null);
        loginRequest.setPassword(null);
        ObjectMapper mapper = new ObjectMapper();
        // when
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(loginRequest);

        // then
        MvcResult login = mockMvc.perform(post("/api/users/login").contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                        .andDo(print())
                        .andExpect(status().is(406))
                        .andReturn();
        String errorMessage = login.getResponse().getErrorMessage();
        assertThat(errorMessage).isEqualTo(null);
    }

}