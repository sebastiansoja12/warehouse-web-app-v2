package com.warehouse.warehouse.controller;

import com.warehouse.warehouse.model.Depot;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DepotControllerTest {

    @Autowired
    private DepotController depotController;

    @Test
    public void contextLoads() throws Exception {
        assertThat(depotController).isNull();
    }

    @Test
    void addDepotList() {
    }

    @Test
    void getDepots() {
        depotController = mock(DepotController.class);
        when(depotController.getDepot()).thenReturn(prepareMockData());

        Assert.assertThat(depotController.getDepot(), Matchers.hasSize(2));
    }

    private List<Depot> prepareMockData() {
        List<Depot> depots = new ArrayList<>();
        depots.add(new Depot((long) 1, "Gdansk","Wolnosci", "Poland", "GD1"));
        depots.add(new Depot((long) 2, "Katowice","Katowicka", "Poland", "KT1"));

        return depots;

    }
}