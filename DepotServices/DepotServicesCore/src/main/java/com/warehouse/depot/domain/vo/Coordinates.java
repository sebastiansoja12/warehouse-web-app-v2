package com.warehouse.depot.domain.vo;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Data
@Value
@Builder
public class Coordinates {
    double lat;
    double lon;
}
