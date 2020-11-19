package com.warehouse.warehouse.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import static javax.persistence.FetchType.LAZY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Parcel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;


    @JsonProperty("kodPaczki")
    private String parcelCode;

    @JsonProperty("czyStandardowa")
    private boolean isCustom;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "id")
    private Depot depot;




}
