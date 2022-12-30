package com.warehouse.supplier;

import com.warehouse.supplier.dto.SupplierAddRequest;
import com.warehouse.supplier.dto.SupplierDto;

import java.util.List;

public interface SupplyService {

    void createSupplier(SupplierAddRequest supplier);

    void createMultipleSuppliers(List<SupplierAddRequest> suppliers);

    List<SupplierDto> findSuppliersByDepotCode(String depotCode);

    SupplierDto findSupplierBySupplierCode(String supplierCode);

    List<SupplierDto> findAllSuppliers();
}
