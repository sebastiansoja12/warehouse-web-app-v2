package com.warehouse.warehouse.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @ManyToOne()
    private Depot depot;
}
