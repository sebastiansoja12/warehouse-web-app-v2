package com.warehouse.service;

import com.warehouse.entity.Depot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
@ActiveProfiles("dev")
class DepotServiceTest {

    @Autowired
    private DepotService depotService;

    @Test
    void shouldGetSingleDepot(){
       // given
       final Long depotId = 1L;
       // when
       final Optional<Depot> depot =  depotService.findById(depotId);
       // then
       assertTrue(depot.isPresent());
       assertThat(depot).isNotNull();
       assertThat(depot.get().getId()).isEqualTo(1L);
    }

    @Test
    void shouldReturnAllDepots(){
        // given && when
        List<Depot> depotList = depotService.getAll();
        // then
        assertThat(depotList).isNotNull();
    }

    @Test
    @Transactional
    void shouldSaveDepot() {
        // given
        final List<Depot> depot = createFakeDepot();
        // when
        depotService.saveAll(depot);
        // then
        assertThat(depot.get(0).getId()).isNotNull();
    }
    @Test
    void shouldThrowDepotNotFoundException() {
        // given
        final Long depotId = 123L;
        // when
        final Optional<Depot> depot = depotService.findById(depotId);
        // then
        assertFalse(depot.isPresent());
    }

    public List<Depot> createFakeDepot() {
        final List<Depot> fakeDepots = new ArrayList<>();

        // depot 1
        final Depot depot1 = Depot.builder()
                .city("Test1")
                .street("Test street1")
                .depotCode("TS1")
                .country("Test1")
                .build();

        fakeDepots.add(depot1);

        return fakeDepots;
    }

    public List<Depot> createWrongDepot() {
        final List<Depot> fakeDepots = new ArrayList<>();

        // depot 1
        final Depot depot1 = Depot.builder()
                .city(null)
                .street(null)
                .depotCode(null)
                .country(null)
                .build();

        fakeDepots.add(depot1);

        return fakeDepots;
    }

}