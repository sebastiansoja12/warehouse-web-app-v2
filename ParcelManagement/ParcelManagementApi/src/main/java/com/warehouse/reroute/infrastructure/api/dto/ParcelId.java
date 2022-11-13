package com.warehouse.reroute.infrastructure.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class ParcelId {
    @NonNull
    Long value;
}
