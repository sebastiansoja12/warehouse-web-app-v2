package com.warehouse.warehouse.dto;

import com.warehouse.warehouse.service.AuthService;
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

    private String authenticationToken;
    private String username;
    private String refreshToken;
    private Instant expiresAt;



    public AuthenticationResponse(String username){
        this.username=username;
    }


}
