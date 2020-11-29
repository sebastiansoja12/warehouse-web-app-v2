package com.warehouse.warehouse.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.AUTO;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="depot")
public class Depot {



    @Id
    @GeneratedValue()
    @org.hibernate.annotations.Type(type="uuid-char")
    private UUID id;


    @JsonProperty("dataWejscia")
    private Instant created_at;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parcel_id", referencedColumnName = "id")
    private Parcel parcel;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "depot_id", referencedColumnName = "id")
    private DepotInformation depotInformation;















}
