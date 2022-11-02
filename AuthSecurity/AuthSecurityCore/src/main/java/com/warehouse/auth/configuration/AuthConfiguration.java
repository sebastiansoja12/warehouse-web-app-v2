package com.warehouse.auth.configuration;

import com.warehouse.auth.domain.port.primary.AuthenticationPort;
import com.warehouse.auth.domain.port.primary.AuthenticationPortImpl;
import com.warehouse.auth.domain.port.secondary.UserRepository;
import com.warehouse.auth.domain.service.*;
import com.warehouse.auth.infrastructure.adapter.AuthenticationAdapter;
import com.warehouse.auth.infrastructure.adapter.AuthenticationReadRepository;
import com.warehouse.auth.infrastructure.adapter.AuthenticationRepositoryImpl;
import com.warehouse.auth.infrastructure.adapter.RefreshTokenReadRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;


@Configuration
@Slf4j
@CrossOrigin
public class AuthConfiguration  {

    @Bean(name = "primaryAuthenticationPort")
    public AuthenticationPort authenticationPort(AuthenticationService authenticationService,
                                                 AuthenticationManager authenticationManager) {
        return new AuthenticationPortImpl(authenticationService, authenticationManager);
    }

    @Bean
    public JwtProvider jwtProvider() {
        return new JwtProviderImpl();
    }

    @Bean
    public AuthenticationService authenticationService(
            com.warehouse.auth.domain.port.secondary.AuthenticationPort authenticationPort,
            JwtProvider jwtProvider) {
        return new AuthenticationServiceImpl(authenticationPort, jwtProvider);
    }

    @Bean
    public com.warehouse.auth.domain.port.secondary.AuthenticationPort authenticationPort(
        UserRepository userRepository,  RefreshTokenService refreshTokenService) {
        return new AuthenticationAdapter(userRepository, refreshTokenService);
    }

    @Bean(name="authenticationRefreshToken")
    public RefreshTokenService refreshTokenService() {
        return new RefreshTokenServiceImpl();
    }

    @Bean(name = "authUserRepository")
    public UserRepository userRepository(AuthenticationReadRepository repository,
        RefreshTokenReadRepository refreshTokenReadRepository) {
        return new AuthenticationRepositoryImpl(repository, refreshTokenReadRepository);
    }
}
