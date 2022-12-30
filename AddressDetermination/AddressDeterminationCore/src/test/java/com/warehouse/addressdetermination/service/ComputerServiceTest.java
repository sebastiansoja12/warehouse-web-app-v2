package com.warehouse.addressdetermination.service;

import com.warehouse.addressdetermination.domain.model.Coordinates;
import com.warehouse.addressdetermination.domain.service.ComputeService;
import com.warehouse.addressdetermination.domain.service.ComputeServiceImpl;
import com.warehouse.depot.api.dto.CoordinatesDto;
import com.warehouse.depot.api.dto.DepotDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ComputerServiceTest {

    private ComputeService computerService = new ComputeServiceImpl();

    @Test
    void shouldCompute() {
        // given
        final List<DepotDto> depotsList = depots();
        final Coordinates coordinates = Coordinates.builder()
                .lon(10.03)
                .lat(13.11)
                .build();
        // when
        final String compute = computerService.computeLength(coordinates, depotsList);
        // then
        assertThat(compute).isEqualTo("TST");
    }

    @Test
    void shouldThrowException() {
        // given
        final List<DepotDto> depotsList = new ArrayList<>();
        final Coordinates coordinates = Coordinates.builder()
                .build();
        // when
        final Executable executable = () -> computerService.computeLength(coordinates, depotsList);
        // then
        final NoSuchElementException exception = assertThrows(NoSuchElementException.class, executable);
        assertThat(exception.getClass()).isInstanceOf(Class.class);
    }


    List<DepotDto> depots() {
        final List<DepotDto> depots = new ArrayList<>();

        final DepotDto depot = new DepotDto();
        depot.setDepotCode("TST");
        depot.setCoordinates(CoordinatesDto.builder()
                .lon(50.0)
                .lat(50.2)
                .build());

        depots.add(depot);

        return depots;
    }
}
