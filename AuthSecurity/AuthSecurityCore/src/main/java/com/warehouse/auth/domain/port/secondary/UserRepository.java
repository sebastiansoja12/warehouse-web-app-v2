package com.warehouse.auth.domain.port.secondary;

import com.warehouse.auth.domain.model.AuthenticationResponse;
import com.warehouse.auth.infrastructure.adapter.secondary.entity.UserEntity;

import java.util.Optional;

public interface UserRepository {

    AuthenticationResponse login(AuthenticationResponse authentication);

    void signup(UserEntity userEntity);

    void logout(String token);

    Optional<UserEntity> findByUsername(String username);
}
