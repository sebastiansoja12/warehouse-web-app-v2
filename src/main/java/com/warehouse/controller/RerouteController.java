package com.warehouse.controller;


import com.warehouse.dto.RerouteRequest;
import com.warehouse.service.RerouteService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/reroute")
public class RerouteController {

    private final RerouteService rerouteService;

    @PostMapping
    public void sendReroutingInformation(@RequestBody RerouteRequest rerouteRequest) {
        rerouteService.sendReroutingInformation(rerouteRequest);
    }

}
