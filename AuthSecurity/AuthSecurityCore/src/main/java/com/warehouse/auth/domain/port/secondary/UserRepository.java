package com.warehouse.auth.domain.port.secondary;

import com.warehouse.auth.domain.model.AuthenticationResponse;
import com.warehouse.auth.domain.model.LoginRequest;
import com.warehouse.auth.domain.model.RegisterRequest;
import com.warehouse.auth.infrastructure.adapter.entity.UserEntity;
import org.springframework.security.core.Authentication;

import java.util.Optional;

public interface UserRepository {

    AuthenticationResponse login(AuthenticationResponse authentication);

    void signup(UserEntity userEntity);

    void logout(String token);

    Optional<UserEntity> findByUsername(String username);
}
