package com.warehouse.controller;


import com.warehouse.dto.RerouteRequest;
import com.warehouse.dto.TokenValidationRequest;
import com.warehouse.entity.RerouteToken;
import com.warehouse.service.RerouteService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("/api/reroute")
public class RerouteController {

    private final RerouteService rerouteService;

    @PostMapping
    public RerouteToken sendReroutingInformation(@RequestBody RerouteRequest rerouteRequest) {
        return rerouteService.sendReroutingInformation(rerouteRequest);
    }

    @PostMapping("/valid")
    public boolean validationToken(@RequestBody TokenValidationRequest token) {
        return rerouteService.tokenValidation(token);
    }
}
