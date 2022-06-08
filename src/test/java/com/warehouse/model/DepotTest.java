package com.warehouse.model;

import lombok.AllArgsConstructor;
import org.apache.catalina.Server;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@AllArgsConstructor
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Server.class)
class DepotTest  {

    private final InMemoryDepotRepository inMemoryDepotRepository =
            new InMemoryDepotRepository();
    @Test
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
        inMemoryDepotRepository.addDepot(depot);

        // And exists
        List<Depot> depotList = inMemoryDepotRepository.getDepotList();
        List<String> depotToFind = inMemoryDepotRepository.findDepotCodes(depotList);

        // Then
        assertTrue(depotToFind.contains(depot.getDepotCode()));
        assertThat(depotToFind).isNotNull();
    }
}
