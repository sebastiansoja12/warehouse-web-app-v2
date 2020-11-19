package com.warehouse.warehouse.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import static javax.persistence.GenerationType.AUTO;
import static javax.persistence.GenerationType.IDENTITY;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="depotInformation")
public class DepotInformation {

    @Id
    @GeneratedValue()
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
