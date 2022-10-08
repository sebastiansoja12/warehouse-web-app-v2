package com.warehouse.reroute.domain.model;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Value
@Data
@Builder
public class Token {
    Integer value;
}
