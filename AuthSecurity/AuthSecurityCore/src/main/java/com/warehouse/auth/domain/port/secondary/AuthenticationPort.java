package com.warehouse.auth.domain.port.secondary;

import com.warehouse.auth.domain.model.AuthenticationResponse;
import com.warehouse.auth.domain.model.LoginRequest;
import com.warehouse.auth.domain.model.RegisterRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;

public interface AuthenticationPort {

    String encode(JwtClaimsSet claimsSet);

    AuthenticationResponse login(Authentication authentication, String token);

    void signup(RegisterRequest registerRequest);

    void logout(String token);
}
