package com.warehouse.warehouse.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
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
    private int parcelId;

    @JsonProperty("parcelCode")
    private String parcelCode;

    @JsonProperty("isCustom")
    private boolean isCustom;

    @ManyToOne
    @JoinColumn(name="username", nullable=false)
    private User user;

    private Instant createdAt;

}
