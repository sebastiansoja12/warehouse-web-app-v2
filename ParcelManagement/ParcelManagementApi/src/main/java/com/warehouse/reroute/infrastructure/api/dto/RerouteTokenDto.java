package com.warehouse.reroute.infrastructure.api.dto;

import lombok.Value;

@Value
public class RerouteTokenDto {
    Long parcelId;
    String email;
}
