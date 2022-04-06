package com.warehouse.warehouse.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "supplier")
public class Supplier {

    @Id
    @GeneratedValue()
    private Long id;

    private String supplierCode;

    private String firstName;

    private String lastName;

    private String telephone;
}
