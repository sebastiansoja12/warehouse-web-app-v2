package com.warehouse.supplier.domain.port.secondary;

import com.warehouse.supplier.domain.model.Supplier;

import java.util.List;

public interface SupplierRepository {
    void create(Supplier supplier);

    List<Supplier> findAll();

    void createMultipleSuppliers(List<Supplier> suppliers);

    List<Supplier> findByDepotCode(String depotCode);

    Supplier findByCode(String supplierCode);
}
