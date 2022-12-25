package com.warehouse.supplier.dto;

import lombok.Data;

@Data
public class SupplierAddRequest {

    private String firstName;

    private String lastName;

    private String telephone;

    private DepotDto depot;
}