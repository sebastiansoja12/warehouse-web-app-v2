package com.warehouse.parcelmanagement.reroute.domain.model;

import lombok.Value;

@Value
public class RerouteResponse {
    Long parcelId;
    Integer token;
}
