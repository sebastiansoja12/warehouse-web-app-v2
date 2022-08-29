package com.warehouse.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.Instant;
import java.util.Random;
import java.util.UUID;

import static javax.persistence.GenerationType.*;

@Data
@Entity(name = "another_token")
public class RerouteToken {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private Integer token = generateToken();

    private Instant createdDate;

    @Column(name = "timeout")
    private Instant expiryDate;

    private String parcelId;

    public Integer generateToken() {
        final Random r = new Random( System.currentTimeMillis() );
        this.token = ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
        return this.token;
    }
}
