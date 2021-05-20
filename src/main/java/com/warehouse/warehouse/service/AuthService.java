package com.warehouse.warehouse.service;


import com.warehouse.warehouse.dto.AuthenticationResponse;
import com.warehouse.warehouse.dto.LoginRequest;
import com.warehouse.warehouse.dto.RefreshTokenRequest;
import com.warehouse.warehouse.dto.RegisterRequest;
import com.warehouse.warehouse.exceptions.WarehouseMailException;
import com.warehouse.warehouse.model.Depot;
import com.warehouse.warehouse.model.User;
import com.warehouse.warehouse.model.VerificationToken;
import com.warehouse.warehouse.repository.DepotRepository;
import com.warehouse.warehouse.repository.UserRepository;
import com.warehouse.warehouse.repository.VerificationTokenRepository;
import com.warehouse.warehouse.security.JwtProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthService {


private final PasswordEncoder passwordEncoder;
private final VerificationTokenRepository verificationTokenRepository;
private final MailService mailService;
private final AuthenticationManager authenticationManager;
private final JwtProvider jwtProvider;
private final RefreshTokenService refreshTokenService;
private final DepotRepository depotRepository;

    private final UserRepository userRepository;


    public void signup(RegisterRequest registerRequest)
    {

        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setRole("worker");
        Optional<Depot> depot = depotRepository.findByDepotCode(registerRequest.getDepotCode());
        user.setDepot(depot.orElseThrow());
        user.setEnabled(true);
        userRepository.save(user);
    }


    void fetchUserAndEnable(VerificationToken verificationToken) {
        String username = verificationToken.getUser().getUsername();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new WarehouseMailException("Uzytkownik nie znaleziony o nazwie - " + username));
        user.setEnabled(true);
        userRepository.save(user);
    }


    public void verifyAccount(String token){
        Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
        fetchUserAndEnable(verificationToken.orElseThrow(() -> new WarehouseMailException("Nieprawidlowy Token")));
    }

    public AuthenticationResponse login(LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = jwtProvider.generateToken(authenticate);
        return AuthenticationResponse.builder()
                .authenticationToken(token)
                .role(userRepository.getRoleByUsername(loginRequest.getUsername()))
                .refreshToken(refreshTokenService.generateRefreshToken().getToken())
                .expiresAt(Instant.now().plusSeconds(jwtProvider.getJwtExpirationInMillis()))
                .username(loginRequest.getUsername())
                .build();
    }

    public AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest){
        refreshTokenService.validateRefreshToken(refreshTokenRequest.getRefreshToken());
        String token = jwtProvider.generateTokenWithUserName(refreshTokenRequest.getUsername());
        return AuthenticationResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenRequest.getRefreshToken())
                .expiresAt(Instant.now().plusSeconds(jwtProvider.getJwtExpirationInMillis()))
                .username(refreshTokenRequest.getUsername())
                .build();
    }

    public boolean isLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
    }

    public Optional<User> getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return  userRepository.getUsersIdByUsername(authentication.getName());

    }
    public List<User> getCurrentUsers(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return  userRepository.getUserByUsername(authentication.getName());

    }
    public Optional<User> getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByUsername(authentication.getName());

    }

}
