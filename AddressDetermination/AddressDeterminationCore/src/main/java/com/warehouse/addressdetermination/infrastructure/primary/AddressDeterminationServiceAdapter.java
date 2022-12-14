package com.warehouse.addressdetermination.infrastructure.primary;

import com.warehouse.addressdetermination.AddressDeterminationService;
import com.warehouse.addressdetermination.domain.port.primary.AddressDeterminationPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AddressDeterminationServiceAdapter implements AddressDeterminationService {

    private final AddressDeterminationPort addressDeterminationPort;

    @Override
    public String findFastestRoute(String requestCity) {
        return addressDeterminationPort.findFastestRoute(requestCity);
    }
}
