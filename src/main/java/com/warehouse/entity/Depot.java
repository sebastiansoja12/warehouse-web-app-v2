package com.warehouse.entity;

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
@Table(name = "depot")
public class Depot {

    @Id
    @GeneratedValue()
    private Long id;

    private String city;

    private String street;

    private String country;

    private String depotCode;

}
