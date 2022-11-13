package com.warehouse.reroute.infrastructure.api.dto;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
public class RerouteRequestDto {
    ParcelId parcelId;
    EmailDto email;
}
