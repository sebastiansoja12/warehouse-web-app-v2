package com.warehouse.auth.domain.port.secondary;

import com.warehouse.auth.domain.model.Depot;

public interface DepotRepository {

    Depot findDepotByCode(String depotCode);

}
