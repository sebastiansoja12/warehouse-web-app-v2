package com.warehouse.depot.domain.port.primary;

import com.warehouse.depot.domain.model.Depot;
import com.warehouse.depot.domain.model.DepotCode;
import com.warehouse.depot.domain.model.DepotId;

public interface DepotPort {
    void add(Depot depot);

    Depot viewDepotById(DepotId depotId);

    Depot viewDepotByCode(DepotCode depotCode);
}
