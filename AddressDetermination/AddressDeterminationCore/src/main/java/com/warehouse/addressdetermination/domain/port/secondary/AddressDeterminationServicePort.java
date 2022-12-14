package com.warehouse.addressdetermination.domain.port.secondary;

public interface AddressDeterminationServicePort {

    String findFastestRoute(String requestCity);
}
