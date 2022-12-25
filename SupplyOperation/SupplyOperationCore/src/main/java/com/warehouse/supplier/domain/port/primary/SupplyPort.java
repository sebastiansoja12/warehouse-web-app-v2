package com.warehouse.supplier.domain.port.primary;

import com.warehouse.supplier.domain.model.Supplier;

import java.util.List;

public interface SupplyPort {

    void createSupplier(Supplier supplier);

    List<Supplier> findAllSuppliers();

    void createMultipleSuppliers(List<Supplier> suppliers);

    List<Supplier> findSuppliersByDepotCode(String depotCode);

    Supplier findSupplierByCode(String supplierCode);
}
