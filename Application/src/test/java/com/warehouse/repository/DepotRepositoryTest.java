package com.warehouse.repository;

import com.warehouse.entity.Depot;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@RunWith(SpringRunner.class)
public class DepotRepositoryTest {

    @Autowired
    private DepotRepository depotRepository;
    @AfterEach
    void tearDown() {
        depotRepository.deleteAll();
    }

    @Test
    public void shouldSaveDepot() {
        // given
        final Depot depot = Depot.builder()
                .city("Test")
                .street("Test street")
                .depotCode("TS1")
                .country("Test")
                .build();

        // when
        depotRepository.save(depot);

        // then
        assertThat(depot.getId()).isGreaterThan(0);
    }

    @Test
    public void shouldReturnDepotByDepotCode() {

        // when
        final Depot depot = Depot.builder()
                .city("Test")
                .street("Test street")
                .depotCode("TS1")
                .country("Test")
                .build();

        depotRepository.save(depot);

        // when
        final Optional<Depot> expected = depotRepository.findByDepotCode("TS1");

        // then
        assertTrue(expected.isPresent());
        assertThat(expected.get()).isNotNull();
    }

    @Test
    public void shouldFindDepotById() {
        // when
        final Depot depot = Depot.builder()
                .city("Test")
                .street("Test street")
                .depotCode("TS1")
                .country("Test")
                .build();

        depotRepository.save(depot);
        final Long depotId = depot.getId();

        // when
        final Optional<Depot> expected = depotRepository.findById(depotId);

        // then
        assertTrue(expected.isPresent());
        assertThat(expected.get()).isNotNull();
        assertThat(expected.get().getId()).isEqualTo(depotId);
    }

    @Test
    public void shouldFindAllDepots() {

        // given
        final List<Depot> depots = createFakeDepots();

        depotRepository.saveAll(depots);

        // when
        final List<Depot> expected = depotRepository.findAll();

        // then
        // and: Depot code is TS1
        assertThat(expected.get(0).getDepotCode()).isEqualTo("TS1");
        // and: Depot code is TS2
        assertThat(expected.get(1).getDepotCode()).isEqualTo("TS2");
        // and: Depot code is TS3
        assertThat(expected.get(2).getDepotCode()).isEqualTo("TS3");

    }

    @Test
    public void shouldGetByDepotCode() {
        // when
        final Depot depot = Depot.builder()
                .city("Test")
                .street("Test street")
                .depotCode("TS1")
                .country("Test")
                .build();

        depotRepository.save(depot);

        // when
        final Depot expected = depotRepository.getByDepotCode("TS1");

        // then
        assertThat(expected).isNotNull();
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
