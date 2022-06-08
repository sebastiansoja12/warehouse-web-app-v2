package com.warehouse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierDto {

    private Long id;

    private String supplierCode;

    private String firstName;

    private String lastName;

    private String telephone;

    private String depotCode;

}
