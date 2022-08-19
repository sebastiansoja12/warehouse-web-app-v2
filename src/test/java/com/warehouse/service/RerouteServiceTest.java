package com.warehouse.service;


import com.warehouse.dto.RerouteRequest;
import com.warehouse.entity.RerouteToken;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class RerouteServiceTest {

    @Autowired
    private RerouteService rerouteService;

    private static final String PARCEL_ID = "2e191c24-be5c-4b65-ac12-f3d806351f20";

    private static final String EMAIL = "sebastian5152@wp.pl";

    @Test
    @Transactional
    void shouldGenerateRerouteToken() {
        // given: build request
        final RerouteRequest rerouteRequest = createRequest();

        // when
        final RerouteToken rerouteToken = rerouteService.generateReroutingToken(rerouteRequest);

        // then
        assertThat(rerouteToken).isNotNull();
    }

    RerouteRequest createRequest() {
        return new RerouteRequest(UUID.fromString(PARCEL_ID), EMAIL);
    }
}
