package com.warehouse.service;

import com.warehouse.entity.Depot;
import com.warehouse.impl.DepotServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class DepotServiceImplTest {

    @Autowired
    private DepotServiceImpl depotServiceImpl;


    @Test
    void shouldGetSingleDepot(){

       Optional<Depot> depot =  depotServiceImpl.findById(1L);
       assertThat(depot).isNotNull();
       assertThat(depot.get().getId()).isEqualTo(1L);
    }

    @Test
    void shouldReturnAllDepots(){
        List<Depot> depotList = depotServiceImpl.findAll();
        assertThat(depotList).isNotNull();

    }

}