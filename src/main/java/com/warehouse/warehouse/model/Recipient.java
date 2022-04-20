package com.warehouse.warehouse.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Recipient {

    @Id
    @GeneratedValue()
    private Long id;

    private String email;

    private String telephone;

    private String firstName;

    private String lastName;

    private String city;

    private String street;

}
