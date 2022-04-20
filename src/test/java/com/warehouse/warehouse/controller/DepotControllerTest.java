package com.warehouse.warehouse.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.warehouse.warehouse.model.Depot;
import com.warehouse.warehouse.repository.DepotRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
class DepotControllerTest {

    @Autowired
    private DepotRepository depotRepository;

    @Autowired
    private DepotController depotController;

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
       MvcResult mvcResult = (MvcResult) mockMvc.perform(MockMvcRequestBuilders.get("/api/depots/" + depot.getId()))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is(200))
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
    public void contextLoads() throws Exception {
        assertThat(depotController).isNull();
    }

    @Test
    @Transactional
    void addDepotList() {
        Depot depotRybnik = new Depot();
        depotRybnik.setCity("Rybnik");
        depotRybnik.setStreet("Kolorowa 12");
        depotRybnik.setCountry("Polska");
        depotRybnik.setDepotCode("RB1");
        Depot depotOpole = new Depot();
        depotOpole.setCity("Opole");
        depotOpole.setStreet("Katowicka 1");
        depotOpole.setCountry("Polska");
        depotOpole.setDepotCode("OPO");
        List<Depot> depotList = new ArrayList<>();
        depotList.add(depotRybnik);
        depotList.add(depotOpole);
        depotRepository.saveAll(depotList);
    }

    @Test
    void getDepots() {
        depotController = mock(DepotController.class);
        when(depotController.getDepot()).thenReturn(prepareMockData());
        assertThat(depotController.getDepot()).isNotNull();
    }

    private List<Depot> prepareMockData() {
        List<Depot> depots = new ArrayList<>();
        depots.add(new Depot((long) 1, "Gdansk","Wolnosci", "Poland", "GD1"));
        depots.add(new Depot((long) 2, "Katowice","Katowicka", "Poland", "KT1"));
        return depots;

    }
}