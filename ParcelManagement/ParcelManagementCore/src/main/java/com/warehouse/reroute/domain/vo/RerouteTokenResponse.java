package com.warehouse.reroute.domain.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;

@Value
@Getter
@Setter
public class RerouteTokenResponse {
    Integer token;
    ParcelId parcelId;
    boolean valid;
}
