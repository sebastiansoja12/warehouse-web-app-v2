package com.warehouse.parcelmanagement.reroute.domain.model;


import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RerouteRequest {

    @NonNull
    private Long parcelId;

    @NonNull
    private String email;
}
