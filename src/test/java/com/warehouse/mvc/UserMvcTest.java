package com.warehouse.mvc;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = {"/application-test.properties"})
@ActiveProfiles("test")
public class UserMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnAllUsers() throws Exception {
        // given && when
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/users/all")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
        // then
                .andExpect(content().json(returnedUser()));
    }

    @Test
    void shouldReturnUserByUsername() throws Exception {
        // given
        final String username = "s-soja";
        // when
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/users/" + username)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
        // then
                .andExpect(content().json(returnedSingleUser()));
    }

    @Test
    void shouldNotReturnUserByUsername() throws Exception {
        // given
        final String username = "wrong-user";
        // when
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/users/" + username)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
        // then
                .andExpect(content().string(emptyJsonResponse()));
    }

    private String emptyJsonResponse() {
        return "";
    }

    private String returnedUser() {
        return "[\n" +
                "   {\n" +
                "      \"id\":1,\n" +
                "      \"username\":\"s-soja\",\n" +
                "      \"firstName\":\"Sebastian\",\n" +
                "      \"lastName\":\"Soja\",\n" +
                "      \"email\":\"sebastian5152@wp.pl\",\n" +
                "      \"depot\":{\n" +
                "         \"id\":2,\n" +
                "         \"city\":\"Gliwice\",\n" +
                "         \"street\":\"Bojowska 21\",\n" +
                "         \"country\":\"Polska\",\n" +
                "         \"depotCode\":\"KT1\"\n" +
                "      }\n" +
                "   }\n" +
                "]";
    }

    private String returnedSingleUser() {
        return "\n" +
                "   {\n" +
                "      \"id\":1,\n" +
                "      \"username\":\"s-soja\",\n" +
                "      \"firstName\":\"Sebastian\",\n" +
                "      \"lastName\":\"Soja\",\n" +
                "      \"email\":\"sebastian5152@wp.pl\",\n" +
                "      \"depot\":{\n" +
                "         \"id\":2,\n" +
                "         \"city\":\"Gliwice\",\n" +
                "         \"street\":\"Bojowska 21\",\n" +
                "         \"country\":\"Polska\",\n" +
                "         \"depotCode\":\"KT1\"\n" +
                "      }\n" +
                "   }\n" +
                "";
    }
}
