package com.warehouse.parcelmanagement.reroute.domain.model;

import lombok.*;

import java.time.Instant;
import java.util.Random;

@Getter
@EqualsAndHashCode
@ToString
@Builder
@AllArgsConstructor
public class RerouteToken {

    private Long id;
    private Integer token;

    private Instant createdDate;

    private Instant expiryDate;

    private String parcelId;

    public Integer generateToken() {
        final Random r = new Random( System.currentTimeMillis() );
        this.token = ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
        return this.token;
    }
}
