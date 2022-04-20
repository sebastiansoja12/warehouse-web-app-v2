package com.warehouse.warehouse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierDto {

    private Long supplierId;

    private String supplierCode;

    private String supplierFirstName;

    private String supplierLastName;

    private String supplierTelephoneNumber;

    private String supplierDepotCode;

}
