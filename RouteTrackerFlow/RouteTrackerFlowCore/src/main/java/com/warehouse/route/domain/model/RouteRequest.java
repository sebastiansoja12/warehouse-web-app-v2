package com.warehouse.route.domain.model;

import lombok.*;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RouteRequest {

    UUID id;
    Long parcelId;
    String username;
    String supplierCode;
    String depotCode;
}
