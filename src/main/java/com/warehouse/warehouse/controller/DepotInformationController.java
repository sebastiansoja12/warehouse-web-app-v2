package com.warehouse.warehouse.controller;

import com.warehouse.warehouse.model.Depot;
import com.warehouse.warehouse.model.DepotInformation;
import com.warehouse.warehouse.service.DepotInformationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class DepotInformationController {

    private final DepotInformationService depotInformationService;


    @PostMapping("/api/depots/information")
    public List<DepotInformation> addDepotInformationList(@RequestBody List<DepotInformation> depotInformationList){
        return depotInformationService.saveAll(depotInformationList);
    }
}
