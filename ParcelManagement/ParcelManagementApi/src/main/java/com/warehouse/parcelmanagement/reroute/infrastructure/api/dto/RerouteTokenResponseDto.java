package com.warehouse.parcelmanagement.reroute.infrastructure.api.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RerouteTokenResponseDto {
    Integer token;
    String parcelId;
    String created;
    String timeout;
    boolean isValid;
}
