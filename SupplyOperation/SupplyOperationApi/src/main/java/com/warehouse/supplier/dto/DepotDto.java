package com.warehouse.supplier.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DepotDto {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long id;

    private String city;

    private String street;

    private String country;

    private String depotCode;

}
