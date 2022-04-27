package com.warehouse.warehouse.controller;


import com.warehouse.warehouse.dto.AuthenticationResponse;
import com.warehouse.warehouse.dto.LoginRequest;
import com.warehouse.warehouse.dto.RefreshTokenRequest;
import com.warehouse.warehouse.dto.RegisterRequest;
import com.warehouse.warehouse.model.User;
import com.warehouse.warehouse.service.AuthService;
import com.warehouse.warehouse.service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/users")
public class AuthController {


    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;

    @Autowired
    public AuthController(AuthService authService, RefreshTokenService refreshTokenService) {
        this.authService = authService;
        this.refreshTokenService = refreshTokenService;
    }
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
    public ResponseEntity<String> logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
        return ResponseEntity.status(OK).body("Refresh Token Deleted Successfully!!");
    }


    @GetMapping("/currentuser")
    public List<User> getCurrentUser() {
        return authService.getCurrentUsers();
    }


/*
    @GetMapping("/currentuser")
    public Optional<User> getCurrentUser(){
        return authService.getCurrentUser();
    }


 */


}
