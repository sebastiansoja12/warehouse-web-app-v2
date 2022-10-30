package com.warehouse.controller;


import com.warehouse.dto.AuthenticationResponse;
import com.warehouse.dto.LoginRequest;
import com.warehouse.dto.RefreshTokenRequest;
import com.warehouse.dto.RegisterRequest;
import com.warehouse.entity.User;
import com.warehouse.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public void signup(@RequestBody RegisterRequest registerRequest) {
        authService.signup(registerRequest);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("refresh/token")
    public AuthenticationResponse refreshToken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        return authService.refreshToken(refreshTokenRequest);
    }

    @PostMapping("/logout")
    public void logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        authService.logout(refreshTokenRequest);
    }


    @GetMapping("/currentuser")
    public List<User> getCurrentUser() {
        return authService.getCurrentUsers();
    }

    @GetMapping("/logged-in")
    public Optional<User> getCurrentLoggedInUser() {
        return authService.findCurrentLoggedInUser();
    }

}
