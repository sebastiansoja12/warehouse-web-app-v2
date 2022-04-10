package com.warehouse.warehouse.controller;

import com.warehouse.warehouse.model.Depot;
import com.warehouse.warehouse.service.DepotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/depots")
public class DepotController {

    private final DepotService depotService;

    @Autowired
    public DepotController(DepotService depotService) {
        this.depotService = depotService;
    }

    @PostMapping()
    public List<Depot> addDepotList(@RequestBody List<Depot> depotList) {
        return depotService.saveAll(depotList);
    }

    @GetMapping()
    public List<Depot> getDepot() {
        return depotService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Depot> getSingleDepot(@PathVariable Long id) {
        return depotService.findById(id);
    }
}
