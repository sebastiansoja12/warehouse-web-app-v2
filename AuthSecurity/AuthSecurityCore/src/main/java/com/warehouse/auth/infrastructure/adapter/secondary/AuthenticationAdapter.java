package com.warehouse.auth.infrastructure.adapter.secondary;

import com.warehouse.auth.domain.model.AuthenticationResponse;
import com.warehouse.auth.domain.model.Depot;
import com.warehouse.auth.domain.model.RegisterRequest;
import com.warehouse.auth.domain.model.User;
import com.warehouse.auth.domain.port.secondary.AuthenticationPort;
import com.warehouse.auth.domain.port.secondary.DepotRepository;
import com.warehouse.auth.domain.port.secondary.UserRepository;
import com.warehouse.auth.domain.service.RefreshTokenService;
import com.warehouse.auth.infrastructure.adapter.secondary.entity.UserEntity;
import com.warehouse.auth.infrastructure.adapter.secondary.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;
import java.util.List;

@AllArgsConstructor
public class AuthenticationAdapter implements AuthenticationPort {

    private final UserRepository userRepository;

    private final DepotRepository depotRepository;

    private final RefreshTokenService refreshTokenService;

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

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
        final UserEntity entity = UserEntity.builder()
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .username(registerRequest.getUsername())
                .depotCode(registerRequest.getDepotCode())
                .email(registerRequest.getEmail())
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .build();
        userRepository.signup(entity);
    }

    @Override
    public void logout(String token) {
        userRepository.logout(token);
    }

    @Override
    public List<User> findCurrentUser(String username) {
        final List<UserEntity> userEntity = userRepository.findByUsername(username);
        final Depot depot = depotRepository.findDepotByCode(userEntity.stream()
                .map(UserEntity::getDepotCode).findAny().get());
        final User user = userMapper.map(userEntity.get(0));
        user.setDepot(depot);
        return List.of(user);
    }
}
