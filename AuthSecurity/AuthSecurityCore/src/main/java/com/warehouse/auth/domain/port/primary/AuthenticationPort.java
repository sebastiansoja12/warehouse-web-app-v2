package com.warehouse.auth.domain.port.primary;

import com.warehouse.auth.domain.model.AuthenticationResponse;
import com.warehouse.auth.domain.model.LoginRequest;
import com.warehouse.auth.domain.model.RefreshTokenRequest;
import com.warehouse.auth.domain.model.RegisterRequest;

public interface AuthenticationPort {

    AuthenticationResponse login(LoginRequest loginRequest);

    void signup(RegisterRequest registerRequest);

    void logout(RefreshTokenRequest refreshTokenRequest);
}
