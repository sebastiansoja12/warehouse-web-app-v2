package com.warehouse.reroute.domain.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RerouteResponse {
    Long parcelId;
    Integer token;
}
