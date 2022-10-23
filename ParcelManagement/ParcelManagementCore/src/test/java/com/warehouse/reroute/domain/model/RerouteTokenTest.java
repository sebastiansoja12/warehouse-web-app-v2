package com.warehouse.reroute.domain.model;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

public class RerouteTokenTest {

    private final static Integer TOKEN = 12345;
    private final static Long EXPIRY_TIME_IN_SECONDS = 600L;

    private final static Long PARCEL_ID = 100001L;
    @Test
    void shouldSuccessfullyBuildRerouteToken() {
        // given && when
        RerouteToken rerouteToken = RerouteToken.builder()
                .token(TOKEN)
                .id(1L)
                .createdDate(Instant.now())
                .expiryDate(Instant.now().plusSeconds(EXPIRY_TIME_IN_SECONDS))
                .parcelId(PARCEL_ID)
                .build();
        // then
        assertThat(rerouteToken.getToken()).isEqualTo(TOKEN);
    }
}
