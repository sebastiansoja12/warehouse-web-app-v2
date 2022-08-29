package com.warehouse.parcelmanagement.reroute.infrastructure.adapter.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;
import java.util.Random;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reroute_token")
@Entity(name = "RerouteToken")
public class RerouteTokenEntity {

    @Id
    @Column(name = "id", nullable = false, unique = true, updatable = false, insertable = false)
    private Long id;

    @Column(name = "token", nullable = false, updatable = false, insertable = false)
    private Integer token = generateToken();

    @Column(name = "created", nullable = false, updatable = false, insertable = false)
    private Instant createdDate;

    @Column(name = "timeout")
    private Instant expiryDate;

    @Column(name = "parcel_id", nullable = false, insertable = false, updatable = false)
    private String parcelId;

    public Integer generateToken() {
        final Random r = new Random( System.currentTimeMillis() );
        this.token = ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
        return this.token;
    }
}
