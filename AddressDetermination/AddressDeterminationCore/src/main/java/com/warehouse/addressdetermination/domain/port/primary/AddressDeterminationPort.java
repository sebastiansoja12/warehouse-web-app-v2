package com.warehouse.addressdetermination.domain.port.primary;

public interface AddressDeterminationPort {

    String findFastestRoute(String requestCity);
}
