package com.warehouse.warehouse.controller;

import com.warehouse.warehouse.model.Depot;
import com.warehouse.warehouse.model.Parcel;
import com.warehouse.warehouse.service.DepotService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class DepotController {

    private final DepotService depotService;

    @GetMapping("/all")
    public List<Depot> getAllDepots(){
        return depotService.findAllDepots();
    }
}
