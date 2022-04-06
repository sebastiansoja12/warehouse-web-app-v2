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
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue()
    private Long id;

    private String firstName;

    private String lastName;

    private String telephoneNumber;

    private String city;

    private String street;

    private String postalCode;

    private String emailAddress;

}
