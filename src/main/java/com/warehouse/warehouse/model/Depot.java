package com.warehouse.warehouse.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.time.Instant;
import java.util.List;
import java.util.Set;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.AUTO;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="depot")
public class Depot {


    @Id
    @GeneratedValue(strategy=AUTO)
    private Long id;


    @JsonProperty("dataWejscia")
    private Instant created_at;


    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private User user;


    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "idPaczki", referencedColumnName = "id")
    private Parcel parcel;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "idOddzialu", referencedColumnName = "id")
    private DepotInformation depotInformation;









}
