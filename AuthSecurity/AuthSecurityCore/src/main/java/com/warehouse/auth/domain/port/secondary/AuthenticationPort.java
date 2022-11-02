package com.warehouse.auth.domain.port.secondary;

import com.warehouse.auth.domain.model.AuthenticationResponse;
import com.warehouse.auth.domain.model.RegisterRequest;
import org.springframework.security.core.Authentication;

public interface AuthenticationPort {

    AuthenticationResponse login(Authentication authentication, String token);

    void signup(RegisterRequest registerRequest);

    void logout(String token);
}
