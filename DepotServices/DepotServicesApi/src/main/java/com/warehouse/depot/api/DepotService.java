package com.warehouse.depot.api;

import com.warehouse.depot.api.dto.DepotCodeDto;
import com.warehouse.depot.api.dto.DepotDto;
import com.warehouse.depot.api.dto.DepotIdDto;

public interface DepotService {

    void add(DepotDto depot);

    DepotDto viewDepotById(DepotIdDto depotIdDto);

    DepotDto viewDepotByCode(DepotCodeDto depotCodeDto);
}
