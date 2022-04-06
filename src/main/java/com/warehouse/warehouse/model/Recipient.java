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
@Entity
@Builder
public class Recipient {

    @Id
    @GeneratedValue
    private Long id;

    @Valid
    @NotBlank
    private String email;

    @Valid
    @NotBlank
    private String telephone;

    @Valid
    @NotBlank
    private String firstName;

    @Valid
    @NotBlank
    private String lastName;

    @Valid
    @NotBlank
    private String city;

    @Valid
    @NotBlank
    private String street;

}
