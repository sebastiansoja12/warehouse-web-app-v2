package com.warehouse.parcelmanagement.reroute.domain.vo;

import lombok.Getter;
import lombok.Value;

import java.time.Instant;

@Value
@Getter
public class RerouteTokenResponse {
    Integer token;
    Long parcelId;
    Instant created;
    Instant timeout;
    boolean valid;


    public boolean isValid() {
        return getTimeout().isAfter(Instant.now());
    }
}
