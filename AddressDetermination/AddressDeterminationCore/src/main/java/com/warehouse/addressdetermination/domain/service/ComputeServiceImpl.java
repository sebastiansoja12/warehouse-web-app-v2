package com.warehouse.addressdetermination.domain.service;

import com.warehouse.addressdetermination.domain.model.Coordinates;
import com.warehouse.depot.api.dto.DepotDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@AllArgsConstructor
@Slf4j
public class ComputeServiceImpl implements ComputeService {

    @Override
    public String computeLength(Coordinates coordinates, List<DepotDto> depots) {
        log.info("Computing fastest route for coordinates: " +
                coordinates.getLat() + ", " + coordinates.getLon());
        final Map<String, Double> cities = new HashMap<>();
        for (DepotDto depot: depots) {
            double lon1 = depot.getCoordinates().getLon();
            double lat1 = depot.getCoordinates().getLat();
            double lat2 = coordinates.getLat();
            double lon2 = coordinates.getLon();

            double result = Math.pow((lat2 - lat1), 2) +  Math.pow((lon2 - lon1), 2);

            cities.put(depot.getDepotCode(), result);
        }
        return Collections.min(cities.entrySet(), Map.Entry.comparingByValue()).getKey();
    }
}
