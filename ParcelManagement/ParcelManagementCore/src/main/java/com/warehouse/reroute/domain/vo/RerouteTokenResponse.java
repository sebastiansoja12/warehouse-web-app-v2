package com.warehouse.reroute.domain.vo;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Value
@Data
@Builder
public class RerouteTokenResponse {
    Integer token;
    ParcelId parcelId;
    boolean valid;
}
