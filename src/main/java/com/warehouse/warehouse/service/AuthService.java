package com.warehouse.warehouse.service;


import com.warehouse.warehouse.dto.AuthenticationResponse;
import com.warehouse.warehouse.dto.LoginRequest;
import com.warehouse.warehouse.dto.RefreshTokenRequest;
import com.warehouse.warehouse.dto.RegisterRequest;
import com.warehouse.warehouse.exceptions.WarehouseMailException;
import com.warehouse.warehouse.model.NotificationEmail;
import com.warehouse.warehouse.model.User;
import com.warehouse.warehouse.model.VerificationToken;
import com.warehouse.warehouse.repository.RefreshTokenRepository;
import com.warehouse.warehouse.repository.UserRepository;
import com.warehouse.warehouse.repository.VerificationTokenRepository;
import com.warehouse.warehouse.security.JwtProvider;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthService {


private final PasswordEncoder passwordEncoder;

private final UserRepository userRepository;

private final VerificationTokenRepository verificationTokenRepository;

private final MailService mailService;

private final AuthenticationManager authenticationManager;

private final JwtProvider jwtProvider;

private final RefreshTokenService refreshTokenService;

    public void signup(RegisterRequest registerRequest)
    {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setRole(registerRequest.getRole());
        user.setEnabled(false);

        userRepository.save(user);

       String token = generateVerificationToken(user);
        mailService.sendMail(new NotificationEmail("Please Activate your Account",
                user.getEmail(), "Thank you for signing up to PDP, " +
                "please click on the below url to activate your account : " +
                "http://localhost:8080/api/users/accountVerification/" + token));
    }

    private String generateVerificationToken(User user) {
        String token = UUID.randomUUID().toString();

        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        verificationTokenRepository.save(verificationToken);
        return token;
    }

    @Transactional
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
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = jwtProvider.generateToken(authenticate);
        return AuthenticationResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenService.generateRefreshToken().getToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .username(loginRequest.getUsername())
                .build();
    }

    public AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest){
        refreshTokenService.validateRefreshToken(refreshTokenRequest.getRefreshToken());
        String token = jwtProvider.generateTokenWithUserName(refreshTokenRequest.getUsername());
        return AuthenticationResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenRequest.getRefreshToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .username(refreshTokenRequest.getUsername())
                .build();
    }

    public boolean isLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
    }
}
