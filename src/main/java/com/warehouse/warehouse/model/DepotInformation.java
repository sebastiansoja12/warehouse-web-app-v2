package com.warehouse.warehouse.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.AUTO;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="depotInformation")
public class DepotInformation {

    @Id
    @GeneratedValue(strategy=AUTO)
    private Long id;

    @JsonProperty("miasto")
    private String city;

    @JsonProperty("ulica")
    private String street;

    @JsonProperty("kraj")
    private String country;

    @JsonProperty("kodOddzialu")
    private String depotCode;
}
