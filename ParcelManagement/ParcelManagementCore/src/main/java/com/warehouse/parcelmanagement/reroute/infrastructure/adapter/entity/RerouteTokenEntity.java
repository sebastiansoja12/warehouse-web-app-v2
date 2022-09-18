package com.warehouse.parcelmanagement.reroute.infrastructure.adapter.entity;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.Random;

@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reroute_token")
@Entity(name = "reroute.RerouteTokenEntity")
public class RerouteTokenEntity {

    @Id
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "token", nullable = false, updatable = false)
    private Integer token = generateToken();

    @Column(name = "created", updatable = false, columnDefinition = "null")
    private Instant createdDate;

    @Column(name = "timeout", updatable = false, columnDefinition = "null")
    private Instant expiryDate;

    @Column(nullable = false, updatable = false)
    private Long parcelId;

    public Integer generateToken() {
        final Random r = new Random( System.currentTimeMillis() );
        this.token = ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
        return this.token;
    }
}
