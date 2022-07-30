package com.warehouse.controller;

import com.warehouse.entity.Depot;
import com.warehouse.impl.DepotServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/depots")
public class DepotController {

    private final DepotServiceImpl depotServiceImpl;

    @Autowired
    public DepotController(DepotServiceImpl depotServiceImpl) {
        this.depotServiceImpl = depotServiceImpl;
    }

    @PostMapping()
    public List<Depot> addDepotList(@RequestBody List<Depot> depotList) {
        return depotServiceImpl.saveAll(depotList);
    }

    @GetMapping()
    public List<Depot> getDepot() {
        return depotServiceImpl.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Depot> getSingleDepot(@PathVariable Long id) {
        return depotServiceImpl.findById(id);
    }
}
