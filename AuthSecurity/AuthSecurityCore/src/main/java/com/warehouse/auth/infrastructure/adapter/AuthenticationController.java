package com.warehouse.auth.infrastructure.adapter;

import com.warehouse.auth.domain.model.AuthenticationResponse;
import com.warehouse.auth.domain.model.LoginRequest;
import com.warehouse.auth.domain.model.RefreshTokenRequest;
import com.warehouse.auth.domain.port.primary.AuthenticationPort;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthenticationController {

    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationController.class);

    private final AuthenticationPort authenticationPort;


    @PostMapping("/token")
    public String token(Authentication authentication) {
        LOG.debug("Token requested for user: '{}'", authentication.getName());
        final String token = authenticationPort.generateToken(authentication);
        LOG.debug("Token granted: {}", token);
        return token;
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
        return authenticationPort.login(loginRequest);
    }

    @PostMapping("/logout")
    public void logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        authenticationPort.logout(refreshTokenRequest);
    }

    @GetMapping
    public String home(Principal principal) {
        return "Hello, " + principal.getName();
    }

    @PreAuthorize("hasAuthority('SCOPE_admin')")
    @GetMapping("/secure")
    public String secure() {
        return "This is secured!";
    }

}
