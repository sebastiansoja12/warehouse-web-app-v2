package com.warehouse.reroute.infrastructure.api.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RerouteTokenResponseDto {
    Integer token;
    Long parcelId;
    String created;
    String timeout;
    boolean isValid;
}
