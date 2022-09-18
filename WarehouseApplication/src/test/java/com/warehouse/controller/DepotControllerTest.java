package com.warehouse.controller;

import com.warehouse.entity.Depot;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("dev")
public class DepotControllerTest {

    @Autowired
    private DepotController depotController;

    @Test
    void shouldReturnOneDepot() {
        // given
        final Long depotId = 1L;
        // when
        final Optional<Depot> depot = depotController.getSingleDepot(depotId);
        // then
        assertThat(depot).isPresent();
        assertThat(depot.get().getDepotCode()).isEqualTo("KT3");
    }

    @Test
    @Transactional
    void shouldSaveDepots() {
        // given
        final List<Depot> depot = createFakeDepots();
        // when
        depotController.addDepotList(depot);
        // then
        assertThat(depot.get(0).getId()).isNotNull();
        assertThat(depot.get(1).getId()).isNotNull();
        assertThat(depot.get(2).getId()).isNotNull();

    }

    @Test
    void shouldReturnAllDepot() {
        // given && when
        final List<Depot> depots = depotController.getDepot();
        // then
        assertThat(depots).isNotNull();
    }

    public List<Depot> createFakeDepots() {
        final List<Depot> fakeDepots = new ArrayList<>();

        // depot 1
        final Depot depot1 = Depot.builder()
                .city("Test1")
                .street("Test street1")
                .depotCode("TS1")
                .country("Test1")
                .build();

        // depot 2
        final Depot depot2 = Depot.builder()
                .city("Test2")
                .street("Test street2")
                .depotCode("TS2")
                .country("Test2")
                .build();

        // depot 3
        final Depot depot3 = Depot.builder()
                .city("Test3")
                .street("Test street3")
                .depotCode("TS3")
                .country("Test3")
                .build();

        fakeDepots.add(depot1);
        fakeDepots.add(depot2);
        fakeDepots.add(depot3);

        return fakeDepots;
    }
}
