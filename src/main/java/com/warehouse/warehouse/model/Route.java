package com.warehouse.warehouse.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="route")
public class Route {



    @Id
    @GeneratedValue()
    @org.hibernate.annotations.Type(type="uuid-char")
    private UUID id;


    private Instant created;


    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "parcel_id", referencedColumnName = "id")
    private Parcel parcel;


    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "depot_id", referencedColumnName = "id")
    private Depot depot;















}
