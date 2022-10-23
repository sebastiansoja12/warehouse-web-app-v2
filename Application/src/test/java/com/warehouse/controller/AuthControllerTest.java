package com.warehouse.controller;

import com.warehouse.dto.AuthenticationResponse;
import com.warehouse.dto.LoginRequest;
import com.warehouse.dto.RegisterRequest;
import com.warehouse.entity.User;
import com.warehouse.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AuthControllerTest {


    @Autowired
    private AuthController authController;

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    void shouldRegisterUser() {
        // given
        final RegisterRequest registerRequest = createRegisterRequest();

        // when
        authController.signup(registerRequest);
        // then
        final Optional<User> user = userRepository.findByUsername(registerRequest.getUsername());
        assertTrue(user.isPresent());
        assertThat(user.get()).isNotNull();
    }

    @Test
    void shouldLoginAndGetContent() {
        // given
        final LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("s-soja");
        loginRequest.setPassword("test");
        // when
        final AuthenticationResponse authenticationResponse = authController.login(loginRequest);

        // then
        assertThat(authenticationResponse.getUsername()).isEqualTo(loginRequest.getUsername());
        assertThat(authenticationResponse.getRefreshToken()).isInstanceOf(String.class);

    }
    @Test
    @Transactional
    void shouldLoginAndGetBadCredentialsError() {
        // given
        final LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(null);
        loginRequest.setPassword(null);
        final String exceptionMessage = "Bad credentials";
        // when
        final BadCredentialsException exception = assertThrows(BadCredentialsException.class, () -> {
            authController.login(loginRequest);
        });
        // then
        assertThat(exception).isInstanceOf(BadCredentialsException.class);
        assertThat(exception.getMessage()).isEqualTo(exceptionMessage);
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
}