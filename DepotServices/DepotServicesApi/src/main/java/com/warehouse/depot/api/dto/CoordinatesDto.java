package com.warehouse.depot.api.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CoordinatesDto {
    double lat;
    double lon;
}
