package com.warehouse.service;

import com.warehouse.dto.RefreshTokenRequest;
import com.warehouse.entity.RefreshToken;
import com.warehouse.exceptions.WarehouseException;
import com.warehouse.repository.RefreshTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken generateRefreshToken() {
        final RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setCreatedDate(Instant.now());
        return refreshTokenRepository.save(refreshToken);

    }

    void validateRefreshToken(String token) {
        refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new WarehouseException("Invalid refresh token"));
    }

    public void deleteRefreshToken(RefreshTokenRequest refreshTokenRequest) {
        refreshTokenRepository.deleteByToken(refreshTokenRequest.getRefreshToken());
    }
}
