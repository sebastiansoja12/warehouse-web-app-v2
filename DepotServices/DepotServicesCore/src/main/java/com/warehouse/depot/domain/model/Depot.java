package com.warehouse.depot.domain.model;

import com.warehouse.depot.domain.vo.Coordinates;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Data
@Value
@Builder
public class Depot {
    String city;

    String street;

    String country;

    String depotCode;

    Coordinates coordinates;
}
