package com.warehouse.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryDepotRepository {

    private final List<Depot> depotList = new ArrayList<>();

    public List<Depot> addDepot(Depot depot) {
        depotList.add(depot);
        return depotList;
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
