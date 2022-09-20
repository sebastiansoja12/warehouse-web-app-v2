package com.warehouse.web.controller;

import com.warehouse.parcelmanagement.reroute.infrastructure.api.RerouteService;
import com.warehouse.parcelmanagement.reroute.infrastructure.api.dto.*;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reroute")
@AllArgsConstructor
public class RerouteTokenController {

    private final RerouteService rerouteService;

    @PostMapping
    RerouteResponseDto sendInformation(@RequestBody RerouteRequestDto request) {
        return rerouteService.sendReroutingInformation(request);
    }
    @GetMapping("/token/{value}")
    RerouteTokenResponseDto getToken(TokenDto token) {
        return rerouteService.findByToken(token);
    }

    @GetMapping("/token/{value}/parcel/{parcelId}")
    RerouteTokenResponseDto loadByTokenAndParcelId(TokenDto token, ParcelIdDto parcel) {
        return rerouteService.loadByTokenAndParcelId(token, parcel);
    }

    @GetMapping("/valid/token/{value}/parcel/{parcelId}")
    boolean isTokenValid(TokenDto token, ParcelIdDto parcel) {
        return rerouteService.loadByTokenAndParcelId(token, parcel).isValid();
    }

}
