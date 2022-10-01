package com.warehouse.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.Instant;
import java.util.Objects;
import java.util.Random;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "warehouseapplication.RerouteTokenEntity")
@Setter
public class RerouteToken {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private Integer token = generateToken();

    private Instant createdDate;

    @Column(name = "timeout")
    private Instant expiryDate;

    private Long parcelId;

    public Integer generateToken() {
        final Random r = new Random( System.currentTimeMillis() );
        this.token = ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
        return this.token;
    }
}
