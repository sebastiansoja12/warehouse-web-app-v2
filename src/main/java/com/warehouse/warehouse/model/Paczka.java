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
public class Paczka {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderId;

    @JsonProperty("kodPaczki")
    private String kodPaczki;

    @JsonProperty("isCustom")
    private boolean isCustom;

    @JsonProperty("username")
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;

    private Instant createdAt;

}
