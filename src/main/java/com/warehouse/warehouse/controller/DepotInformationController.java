package com.warehouse.warehouse.controller;

import com.warehouse.warehouse.model.Depot;
import com.warehouse.warehouse.model.DepotInformation;
import com.warehouse.warehouse.service.DepotInformationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class DepotInformationController {

    private final DepotInformationService depotInformationService;

    @PostMapping("/api/depots/information")
    public DepotInformation addDepotInformation(@RequestBody DepotInformation depotInformation){
        return depotInformationService.save(depotInformation);
    }
}
