package com.warehouse.auth.infrastructure.adapter.secondary;

import com.warehouse.auth.domain.model.*;
import com.warehouse.auth.domain.port.primary.AuthenticationPort;
import lombok.AllArgsConstructor;
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

    @PostMapping("/signup")
    public void signup(@RequestBody RegisterRequest registerRequest) {
        authenticationPort.signup(registerRequest);
    }

    @PostMapping("/logout")
    public void logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        authenticationPort.logout(refreshTokenRequest);
    }

    @GetMapping("/current-user")
    public User currentUser() {
        return authenticationPort.findCurrentUser();
    }

    @GetMapping("/{username}")
    public User findUserByUsername(@PathVariable String username) {
        return authenticationPort.findUserByUsername(username);
    }
}
