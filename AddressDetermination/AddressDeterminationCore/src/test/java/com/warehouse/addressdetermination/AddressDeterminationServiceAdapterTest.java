package com.warehouse.addressdetermination;

import com.warehouse.addressdetermination.domain.port.primary.AddressDeterminationPort;
import com.warehouse.addressdetermination.infrastructure.adapter.primary.AddressDeterminationServiceAdapter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AddressDeterminationServiceAdapterTest {


    @Mock
    private AddressDeterminationPort addressDeterminationPort;

    @InjectMocks
    private AddressDeterminationServiceAdapter adapter;

    @Test
    void shouldFindFastestRoute() {
        // given
        final String requestCity = "Test";
        final String expectedDepotCode = "TST";
        when(adapter.findFastestRoute(requestCity)).thenReturn(expectedDepotCode);
        // when
        final String actualDepotCode = adapter.findFastestRoute(requestCity);
        // then
        assertEquals(actualDepotCode, expectedDepotCode);
    }


}
