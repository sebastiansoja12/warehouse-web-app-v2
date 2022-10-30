package com.warehouse.auth.domain.port.primary;

import com.warehouse.auth.domain.model.AuthenticationResponse;
import com.warehouse.auth.domain.model.LoginRequest;
import com.warehouse.auth.domain.model.RefreshTokenRequest;
import com.warehouse.auth.domain.model.RegisterRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;

public interface AuthenticationPort {

    String generateToken(Authentication authentication);

    AuthenticationResponse login(LoginRequest loginRequest);

    void signup(RegisterRequest registerRequest);

    void logout(RefreshTokenRequest refreshTokenRequest);
}
