package com.warehouse.warehouse.controller;

import com.warehouse.warehouse.model.Depot;
import com.warehouse.warehouse.service.DepotService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/depots")
public class DepotController {

    private final DepotService depotService;


    @PostMapping()
    public List<Depot> addDepotList(@RequestBody List<Depot> depotList){
        return depotService.saveAll(depotList);
    }
    @GetMapping("/all")
    public List<Depot> getDepot(){
        return depotService.getAll();
    }
}
