package com.warehouse.addressdetermination.domain.port.primary;

import com.warehouse.addressdetermination.domain.port.secondary.AddressDeterminationServicePort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AddressDeterminationPortImpl implements AddressDeterminationPort {

    private final AddressDeterminationServicePort determinationServicePort;
    @Override
    public String findFastestRoute(String requestCity) {
        return determinationServicePort.findFastestRoute(requestCity);
    }
}
