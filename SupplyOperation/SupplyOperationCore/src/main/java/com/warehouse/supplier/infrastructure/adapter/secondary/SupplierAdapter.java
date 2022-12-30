package com.warehouse.supplier.infrastructure.adapter.secondary;

import com.warehouse.supplier.domain.model.Supplier;
import com.warehouse.supplier.domain.port.secondary.SupplierRepository;
import com.warehouse.supplier.domain.port.secondary.SupplierServicePort;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class SupplierAdapter implements SupplierServicePort {

    private final SupplierRepository supplierRepository;

    @Override
    public void create(Supplier supplier) {
        supplierRepository.create(supplier);
    }

    @Override
    public List<Supplier> findAll() {
        return supplierRepository.findAll();
    }

    @Override
    public void createMultipleSuppliers(List<Supplier> suppliers) {
        supplierRepository.createMultipleSuppliers(suppliers);
    }

    @Override
    public List<Supplier> findSuppliersByDepotCode(String depotCode) {
        return supplierRepository.findByDepotCode(depotCode);
    }

    @Override
    public Supplier findSupplierByCode(String supplierCode) {
        return supplierRepository.findByCode(supplierCode);
    }
}
