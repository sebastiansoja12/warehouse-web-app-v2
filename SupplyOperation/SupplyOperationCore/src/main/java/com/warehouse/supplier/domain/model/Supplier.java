package com.warehouse.supplier.domain.model;

import lombok.Data;

@Data
public class Supplier {

    private String supplierCode;

    private String firstName;

    private String lastName;

    private String telephone;

    private Depot depot;

}
