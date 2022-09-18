package com.warehouse.service;


import com.warehouse.dto.AuthenticationResponse;
import com.warehouse.dto.LoginRequest;
import com.warehouse.dto.RefreshTokenRequest;
import com.warehouse.dto.RegisterRequest;
import com.warehouse.entity.RefreshToken;
import com.warehouse.entity.User;
import com.warehouse.repository.RefreshTokenRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("dev")
public class AuthServiceTest {

    @Autowired
    private AuthService authService;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Test
    @Transactional
    void shouldRegisterUser() {
        // given
        final RegisterRequest registerRequest = createRegisterRequest();

        // when
        authService.signup(registerRequest);
        // then
        final User user = authService.findByUsername(registerRequest.getUsername());
        assertThat(user).isNotNull();
    }

    @Test
    @Transactional
    void shouldNotRegisterUser() {
        // given
        final RegisterRequest registerRequest = createRegisterRequestWithoutEmail();

        // when
        final Executable executable = () -> authService.signup(registerRequest);
        final ConstraintViolationException exception = assertThrows(ConstraintViolationException.class, executable);
        // then
        assertThat(exception).isInstanceOf(ConstraintViolationException.class);
        assertThat(exception.getMessage()).isEqualTo(constraintViolationExceptionMessage());
    }

    @Test
    void shouldLoginUser() {
        // given
        final LoginRequest loginRequest = createLoginRequest();
        // when
        final AuthenticationResponse authenticationResponse = authService.login(loginRequest);
        // then
        assertThat(authenticationResponse.getUsername()).isEqualTo(loginRequest.getUsername());
        assertThat(authenticationResponse.getRefreshToken()).isInstanceOf(String.class);
    }

    @Test
    void shouldNotLogin() {
        // given
        final LoginRequest loginRequest = new LoginRequest();
        final String exceptionMessage = "Bad credentials";
        // when
        final BadCredentialsException exception = assertThrows(BadCredentialsException.class, () -> {
            authService.login(loginRequest);
        });
        // then
        assertThat(exception).isInstanceOf(BadCredentialsException.class);
        assertThat(exception.getMessage()).isEqualTo(exceptionMessage);
    }

    @Test
    @Transactional
    void shouldLogoutUser() {
        // given
        final LoginRequest loginRequest = createLoginRequest();
        // when
        final AuthenticationResponse authenticationResponse = authService.login(loginRequest);
        final RefreshTokenRequest refreshTokenRequest = refreshTokenRequest(authenticationResponse);
        authService.logout(refreshTokenRequest);
        // then
        final Optional<RefreshToken> refreshToken =
                refreshTokenRepository.findByToken(refreshTokenRequest.getRefreshToken());
        assertThat(refreshToken).isEmpty();
    }

    @Test
    @Transactional
    void shouldSuccessfullyRegisterAndLogin() {
        // given
        final RegisterRequest registerRequest = createRegisterRequest();
        final LoginRequest loginRequest = createLoginRequest();

        // when
        authService.signup(registerRequest);
        final AuthenticationResponse authenticationResponse = authService.login(loginRequest);

        // then
        assertThat(authenticationResponse).isNotNull();
        assertThat(authenticationResponse.getUsername()).isEqualTo(loginRequest.getUsername());


        // after
        final RefreshTokenRequest refreshTokenRequest = refreshTokenRequest(authenticationResponse);
        final Optional<RefreshToken> refreshToken =
                refreshTokenRepository.findByToken(refreshTokenRequest.getRefreshToken());

        assertThat(refreshToken).isNotEmpty();
    }

    private RefreshTokenRequest refreshTokenRequest(AuthenticationResponse response) {
        final RefreshTokenRequest refreshTokenRequest = new RefreshTokenRequest();
        refreshTokenRequest.setRefreshToken(response.getRefreshToken());
        refreshTokenRequest.setUsername(response.getUsername());
        return refreshTokenRequest;
    }

    private LoginRequest createLoginRequest() {
        final LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("s-soja");
        loginRequest.setPassword("test");
        return loginRequest;
    }

    private RegisterRequest createRegisterRequest() {
        final RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setFirstName("test");
        registerRequest.setLastName("test");
        registerRequest.setUsername("test");
        registerRequest.setPassword("test");
        registerRequest.setDepotCode("KT1");
        registerRequest.setEmail("test@test.pl");

        return registerRequest;
    }

    private RegisterRequest createRegisterRequestWithoutEmail() {
        final RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setFirstName("test");
        registerRequest.setLastName("test");
        registerRequest.setUsername("test");
        registerRequest.setPassword("test");
        registerRequest.setDepotCode("KT1");

        return registerRequest;
    }

    public String constraintViolationExceptionMessage() {
        return "Validation failed for classes [com.warehouse.entity.User] during persist time for groups " +
                "[javax.validation.groups.Default, ]\n" +
                "List of constraint violations:[\n" +
                "\tConstraintViolationImpl{interpolatedMessage='darf nicht leer sein', propertyPath=email, " +
                "rootBeanClass=class com.warehouse.entity.User, " +
                "messageTemplate='{javax.validation.constraints.NotBlank.message}'}\n" +
                "]";
    }
}
