package com.warehouse.parcelmanagement.reroute.infrastructure.adapter.controller;

import com.warehouse.parcelmanagement.reroute.domain.model.RerouteRequest;
import com.warehouse.parcelmanagement.reroute.domain.model.RerouteResponse;
import com.warehouse.parcelmanagement.reroute.domain.port.primary.RerouteTokenPort;
import com.warehouse.parcelmanagement.reroute.domain.vo.RerouteTokenResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reroute")
@AllArgsConstructor
public class RerouteTokenController {

    private final RerouteTokenPort rerouteTokenPort;

    @GetMapping
    RerouteResponse sendInformation(@RequestBody RerouteRequest request) {
        return rerouteTokenPort.sendReroutingInformation(request);
    }
    @GetMapping("/{token}")
    RerouteTokenResponse getTokenById(@PathVariable Integer token) {
        return rerouteTokenPort.findByToken(token);
    }

}
