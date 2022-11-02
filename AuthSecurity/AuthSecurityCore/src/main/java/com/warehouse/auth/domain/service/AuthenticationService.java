package com.warehouse.auth.domain.service;

import com.warehouse.auth.domain.model.AuthenticationResponse;
import com.warehouse.auth.domain.model.LoginRequest;
import com.warehouse.auth.domain.model.RegisterRequest;
import org.springframework.security.core.Authentication;

public interface AuthenticationService {

    AuthenticationResponse login(Authentication authentication);

    void signup(RegisterRequest registerRequest);

    void logout(String token);
}
