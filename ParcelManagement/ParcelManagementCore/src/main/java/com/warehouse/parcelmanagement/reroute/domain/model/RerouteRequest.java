package com.warehouse.parcelmanagement.reroute.domain.model;


import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RerouteRequest {

    @NonNull
    private Long parcelId;

    @NonNull
    private String email;
}
