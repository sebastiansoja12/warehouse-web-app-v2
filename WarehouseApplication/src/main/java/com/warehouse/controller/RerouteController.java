package com.warehouse.controller;


import com.warehouse.dto.RerouteRequest;
import com.warehouse.dto.TokenValidationRequest;
import com.warehouse.dto.UpdateParcelRequest;
import com.warehouse.entity.RerouteToken;
import com.warehouse.service.RerouteService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


//@RestController("/api/reroute")
@AllArgsConstructor
public class RerouteController {
/*
    private final RerouteService rerouteService;

    @PostMapping("/information")
    public RerouteToken sendReroutingInformation(@RequestBody RerouteRequest rerouteRequest) {
        return rerouteService.sendReroutingInformation(rerouteRequest);
    }

    @PostMapping("/valid")
    public boolean validationToken(@RequestBody TokenValidationRequest token) {
        return rerouteService.tokenValidation(token);
    }

    @PostMapping()
    public void updateParcel(@RequestBody UpdateParcelRequest parcelRequest) {
        rerouteService.updateParcel(parcelRequest);
    }

 */
}
