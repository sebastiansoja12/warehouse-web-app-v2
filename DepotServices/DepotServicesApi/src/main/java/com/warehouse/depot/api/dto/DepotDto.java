package com.warehouse.depot.api.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepotDto {

    String city;

    String street;

    String country;

    String depotCode;

    CoordinatesDto coordinates;
}
