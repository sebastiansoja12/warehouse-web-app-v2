package com.warehouse.mvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.warehouse.entity.Depot;
import com.warehouse.repository.DepotRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations="classpath:application.properties")

class DepotMvcTest {

    @Autowired
    private DepotRepository depotRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Transactional
    void shouldGetSingleDepot() throws Exception {
        //given
        Depot depot = new Depot();
        depot.setCity("Rybnik");
        depot.setStreet("Kolorowa 12");
        depot.setCountry("Polska");
        depot.setDepotCode("RB1");
        depotRepository.save(depot);
        //when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/depots/" + depot.getId()))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();
        //then
        Depot depotToTest = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Depot.class);

        assertThat(depotToTest).isNotNull();
        assertThat(depotToTest.getCountry()).isEqualTo("Polska");
        assertThat(depotToTest.getCity()).isEqualTo("Rybnik");
        assertThat(depotToTest.getStreet()).isEqualTo("Kolorowa 12");
        assertThat(depotToTest.getDepotCode()).isEqualTo("RB1");
    }

    @Test
    @Transactional
    void shouldThrowServerError() throws Exception {
        // given
        Depot depot = new Depot();
        depot.setCity("Rybnik");
        depot.setStreet("Kolorowa 12");
        depot.setCountry("Polska");
        depot.setDepotCode("RB1");
        depotRepository.save(depot);

        //when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/depots/" + 0))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();

        Depot depotToTest = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Depot.class);

        // then
        assertThat(depotToTest).isNull();
    }

    @Test
    @Transactional
    void shouldNotInsertIncorrectData() throws Exception {
        // given
        Depot depot = new Depot();
        depotRepository.save(depot);

        // when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/depots" + depot))
                .andDo(print())
                .andExpect(status().is(403))
                .andReturn();

        // AND expect
        MvcResult returnNothing = mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/depots/" + depot.getDepotCode()))
                .andDo(print())
                .andExpect(status().is(500))
                .andReturn();


        // then
        assertThat(mvcResult.getResponse().getContentAsString()).isNotNull();
        // TODO -- analyse
        //assertThat(returnNothing.getResponse().getContentAsString()).isEqualTo(getError());
    }


    @Test
    void getAllDepotsAPI() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/depots")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    void getDepotByDepotCodeAPI() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/depots/{depotCode}", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.depotCode").value("KT3"));
    }
}