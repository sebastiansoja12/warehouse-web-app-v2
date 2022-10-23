package com.warehouse.controller;


import com.warehouse.dto.RerouteRequest;
import com.warehouse.dto.TokenValidationRequest;
import com.warehouse.dto.UpdateParcelRequest;
import com.warehouse.entity.RerouteToken;
import com.warehouse.service.TemporaryReroute;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/reroute")
@AllArgsConstructor
public class RerouteController {

    private final TemporaryReroute temporaryReroute;

    @PostMapping("/information")
    public RerouteToken sendReroutingInformation(@RequestBody RerouteRequest rerouteRequest) {
        return temporaryReroute.sendReroutingInformation(rerouteRequest);
    }

    @PostMapping("/valid")
    public boolean validationToken(@RequestBody TokenValidationRequest token) {
        return temporaryReroute.tokenValidation(token);
    }

    @PostMapping()
    public void updateParcel(@RequestBody UpdateParcelRequest parcelRequest) {
        temporaryReroute.updateParcel(parcelRequest);
    }

}
