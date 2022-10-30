package com.warehouse.auth.infrastructure.adapter;

import com.warehouse.auth.domain.model.AuthenticationResponse;
import com.warehouse.auth.domain.model.RegisterRequest;
import com.warehouse.auth.domain.port.secondary.AuthenticationPort;
import com.warehouse.auth.domain.port.secondary.UserRepository;
import com.warehouse.auth.domain.service.RefreshTokenService;
import com.warehouse.auth.domain.vo.AuthenticationToken;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;

import java.time.Instant;

@AllArgsConstructor
public class AuthenticationAdapter implements AuthenticationPort {

    private final JwtEncoder encoder;

    private final UserRepository userRepository;

    private final String ROLE = "user";

    private final RefreshTokenService refreshTokenService;

    @Override
    public String encode(JwtClaimsSet claims) {
        return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    @Override
    public AuthenticationResponse login(Authentication authentication, String token) {

        final AuthenticationResponse response = AuthenticationResponse.builder()
                .authenticationToken(new AuthenticationToken(token))
                .role(ROLE)
                .refreshToken(refreshTokenService.generateRefreshToken().getToken())
                .createdAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(600L))
                .username(authentication.getName())
                .build();

        return userRepository.login(response);
    }

    @Override
    public void signup(RegisterRequest registerRequest) {
        userRepository.signup(registerRequest);
    }

    @Override
    public void logout(String token) {
        userRepository.logout(token);
    }
}
