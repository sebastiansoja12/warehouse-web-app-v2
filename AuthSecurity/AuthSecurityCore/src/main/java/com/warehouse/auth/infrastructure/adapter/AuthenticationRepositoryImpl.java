package com.warehouse.auth.infrastructure.adapter;

import com.warehouse.auth.domain.model.AuthenticationResponse;
import com.warehouse.auth.domain.model.RegisterRequest;
import com.warehouse.auth.domain.port.secondary.UserRepository;
import com.warehouse.auth.infrastructure.adapter.entity.RefreshTokenEntity;
import com.warehouse.auth.infrastructure.adapter.entity.UserEntity;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class AuthenticationRepositoryImpl implements UserRepository {

    private final AuthenticationReadRepository repository;

    private final RefreshTokenReadRepository refreshTokenReadRepository;


    @Override
    public AuthenticationResponse login(AuthenticationResponse authentication) {
        final RefreshTokenEntity refreshToken = new RefreshTokenEntity();
        refreshToken.setToken(authentication.getRefreshToken());
        refreshToken.setCreatedDate(authentication.getCreatedAt());
        refreshTokenReadRepository.save(refreshToken);
        return authentication;
    }

    @Override
    public void signup(RegisterRequest registerRequest) {

    }

    @Override
    public void logout(String token) {
        refreshTokenReadRepository.deleteByToken(token);
    }

    @Override
    public Optional<UserEntity> findByUsername(String username) {
        return repository.findByUsername(username);
    }
}
