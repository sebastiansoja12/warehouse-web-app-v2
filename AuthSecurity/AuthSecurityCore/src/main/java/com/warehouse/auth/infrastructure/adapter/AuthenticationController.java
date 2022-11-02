package com.warehouse.auth.infrastructure.adapter;

import com.warehouse.auth.domain.model.AuthenticationResponse;
import com.warehouse.auth.domain.model.LoginRequest;
import com.warehouse.auth.domain.model.RefreshTokenRequest;
import com.warehouse.auth.domain.port.primary.AuthenticationPort;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthenticationController {


    private final AuthenticationPort authenticationPort;


    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
        return authenticationPort.login(loginRequest);
    }

    @PostMapping("/logout")
    public void logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        authenticationPort.logout(refreshTokenRequest);
    }

    @PreAuthorize("hasAuthority('[admin]')")
    @GetMapping("/secure")
    public String secure() {
        return "This is secured!";
    }

}
