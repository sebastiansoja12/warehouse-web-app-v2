package com.warehouse.parcelmanagement.reroute.infrastructure.adapter.primary;

import com.warehouse.parcelmanagement.reroute.domain.port.primary.RerouteTokenPort;
import com.warehouse.parcelmanagement.reroute.infrastructure.adapter.primary.mapper.PrimaryRequestMapper;
import com.warehouse.parcelmanagement.reroute.infrastructure.adapter.primary.mapper.PrimaryResponseMapper;
import com.warehouse.parcelmanagement.reroute.infrastructure.api.RerouteService;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

@AllArgsConstructor
public class RerouteTokenServiceAdapterTest {

    /*
    private RerouteTokenServiceAdapter adapter;

    @Mock
    private final RerouteTokenPort port;

    @Mock
    private final PrimaryRequestMapper requestMapper;

    @Mock
    private final PrimaryResponseMapper responseMapper;

     */

    @BeforeEach
    void setUp() {
        //adapter = new RerouteTokenServiceAdapter(port, requestMapper, responseMapper);
    }

    @Test
    void shouldFindByToken() {

    }
}
