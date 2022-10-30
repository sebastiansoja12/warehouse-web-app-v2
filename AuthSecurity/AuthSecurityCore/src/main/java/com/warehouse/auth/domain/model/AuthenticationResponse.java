package com.warehouse.auth.domain.model;

import com.warehouse.auth.domain.vo.AuthenticationToken;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationResponse {

    private AuthenticationToken authenticationToken;
    private String username;
    private String refreshToken;
    private Instant createdAt;
    private Instant expiresAt;
    private String role;

}
