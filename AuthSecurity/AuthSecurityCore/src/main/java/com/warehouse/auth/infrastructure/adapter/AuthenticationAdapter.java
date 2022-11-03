package com.warehouse.auth.infrastructure.adapter;

import com.warehouse.auth.domain.model.AuthenticationResponse;
import com.warehouse.auth.domain.model.RegisterRequest;
import com.warehouse.auth.domain.port.secondary.AuthenticationPort;
import com.warehouse.auth.domain.port.secondary.UserRepository;
import com.warehouse.auth.domain.service.RefreshTokenService;
import com.warehouse.auth.infrastructure.adapter.entity.UserEntity;
import com.warehouse.auth.infrastructure.adapter.mapper.RequestToEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;

import java.time.Instant;

@AllArgsConstructor
public class AuthenticationAdapter implements AuthenticationPort {

    private final UserRepository userRepository;

    private final RefreshTokenService refreshTokenService;

    private final RequestToEntityMapper requestToEntityMapper;

    @Override
    public AuthenticationResponse login(Authentication authentication, String token) {

        final AuthenticationResponse response = AuthenticationResponse.builder()
                .authenticationToken(token)
                .role(authentication.getAuthorities().toString())
                .refreshToken(refreshTokenService.generateRefreshToken().getToken())
                .createdAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(3600L))
                .username(authentication.getName())
                .build();

        return userRepository.login(response);
    }

    @Override
    public void signup(RegisterRequest registerRequest) {
        final UserEntity entity = requestToEntityMapper.map(registerRequest);
        userRepository.signup(entity);
    }

    @Override
    public void logout(String token) {
        userRepository.logout(token);
    }
}
