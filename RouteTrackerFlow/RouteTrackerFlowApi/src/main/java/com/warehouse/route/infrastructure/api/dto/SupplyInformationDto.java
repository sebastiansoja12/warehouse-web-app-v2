package com.warehouse.route.infrastructure.api.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class SupplyInformationDto {

    UUID id;
    String username;
    Long parcelId;
    LocalDateTime created;
    String depotCode;
    String supplierCode;

}
