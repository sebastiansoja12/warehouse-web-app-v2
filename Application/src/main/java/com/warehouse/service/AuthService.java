package com.warehouse.service;


import com.warehouse.dto.AuthenticationResponse;
import com.warehouse.dto.LoginRequest;
import com.warehouse.dto.RefreshTokenRequest;
import com.warehouse.dto.RegisterRequest;
import com.warehouse.entity.Depot;
import com.warehouse.entity.User;
import com.warehouse.exceptions.WarehouseException;
import com.warehouse.repository.DepotRepository;
import com.warehouse.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;
    private final DepotRepository depotRepository;
    private final UserRepository userRepository;

    public void signup(RegisterRequest registerRequest) {
        final User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(registerRequest.getPassword());
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        final Depot depot = depotRepository.getByDepotCode(registerRequest.getDepotCode());
        user.setDepot(depot);
        userRepository.save(user);
    }

    public AuthenticationResponse login(LoginRequest loginRequest) {
        final Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);

        final String token = null;
        log.info("Token for user: " + loginRequest.getUsername() + " has been saved. Logging in");

        return AuthenticationResponse.builder()
                .authenticationToken(token)
                .role(userRepository.getRoleByUsername(loginRequest.getUsername()))
                .refreshToken(refreshTokenService.generateRefreshToken().getToken())
                //.expiresAt(Instant.now().plusSeconds(oldJwtProvider.getJwtExpirationInMillis()))
                .username(loginRequest.getUsername())
                .build();
    }

    public AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.validateRefreshToken(refreshTokenRequest.getRefreshToken());
        final String token = null;//oldJwtProvider.generateTokenWithUserName(refreshTokenRequest.getUsername());
        return AuthenticationResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenRequest.getRefreshToken())
                //.expiresAt(Instant.now().plusSeconds(oldJwtProvider.getJwtExpirationInMillis()))
                .username(refreshTokenRequest.getUsername())
                .build();
    }

    public void logout(RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.deleteRefreshToken(refreshTokenRequest);
        log.info("Token of user: " + refreshTokenRequest.getUsername() + " has been successfully deleted" +
                ". Logging out");
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new WarehouseException("User not found"));
    }

    public User getCurrentUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.getUsersIdByUsername(authentication.getName());

    }

    public Optional<User> findCurrentLoggedInUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByUsername(authentication.getName());

    }

    public List<User> getCurrentUsers() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.getUserByUsername(authentication.getName());

    }
}
