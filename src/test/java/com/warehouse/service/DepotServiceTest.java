package com.warehouse.service;

import com.warehouse.model.Depot;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class DepotServiceTest {

    @Autowired
    private DepotService depotService;


    @Test
    void shouldGetSingleDepot(){

       Optional<Depot> depot =  depotService.findById(1L);
       assertThat(depot).isNotNull();
       assertThat(depot.get().getId()).isEqualTo(1L);
    }

    @Test
    void shouldReturnAllDepots(){
        List<Depot> depotList = depotService.getAll();
        assertThat(depotList).isNotNull();

    }

}