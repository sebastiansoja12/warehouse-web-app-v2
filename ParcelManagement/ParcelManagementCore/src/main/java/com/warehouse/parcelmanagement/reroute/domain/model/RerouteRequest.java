package com.warehouse.parcelmanagement.reroute.domain.model;


import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Builder
@Data
public class RerouteRequest {

    @NonNull
    private String id;

    @NonNull
    private Parcel parcel;

    @NonNull
    private Integer token;
}
