package com.warehouse.model;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

// To recreate in future tasks
class DepotTest  {

    @Autowired
    private InMemoryDepotRepository inMemoryDepotRepository;

    private final List<Depot> depotList = new ArrayList<>();

    public List<Depot> addDepot(Depot depot) {
        depotList.add(depot);
        return depotList;
    }

    @Test
    @Disabled
    void shouldReturnDepot() {

        // Given
        final Depot depot = Depot.builder()
                .id(1L)
                .street("Test")
                .city("Test")
                .country("Test")
                .depotCode("TEST")
                .build();

        final List<Depot> depots = List.of(depot);

        // When
        addDepot(depot);

        // And exists
        List<Depot> depotList = getDepotList();
        List<String> depotToFind = findDepotCodes(depotList);

        // Then
        assertTrue(depotToFind.contains(depot.getDepotCode()));
        assertThat(depotToFind).isNotNull();
    }

    public List<Depot> findDepots(List<Depot> depots, String depotCode) {
        return depots.stream()
                .filter(p -> p.getDepotCode().equals(depotCode))
                .collect(Collectors.toList());
    }

    public List<String> findDepotCodes(List<Depot> depots) {
        return depots
                .stream()
                .map(Depot::getDepotCode)
                .collect(Collectors.toList());
    }
    public List<Depot> getDepotList() {
        return depotList;
    }
}
