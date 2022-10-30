package com.warehouse.auth.domain.service;

import com.warehouse.auth.domain.model.AuthenticationResponse;
import com.warehouse.auth.domain.model.LoginRequest;
import com.warehouse.auth.domain.model.RegisterRequest;
import com.warehouse.auth.domain.port.secondary.AuthenticationPort;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationPort authenticationPort;

    @Override
    public String generateToken(Authentication authentication) {

        final JwtClaimsSet claims = JwtClaimsSet.builder()
                    .issuer("self")
                    .issuedAt(Instant.now())
                    .expiresAt(Instant.now().plus(1, ChronoUnit.HOURS))
                    .subject(authentication.getName())
                    .build();

         return authenticationPort.encode(claims);

    }

    @Override
    public AuthenticationResponse login(Authentication authentication) {
        final String token = generateToken(authentication);
        return authenticationPort.login(authentication, token);
    }

    @Override
    public void signup(RegisterRequest registerRequest) {
        authenticationPort.signup(registerRequest);
    }

    @Override
    public void logout(String token) {
        authenticationPort.logout(token);
    }


}
