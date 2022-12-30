package com.warehouse.supplier.domain.port.primary;

import com.warehouse.supplier.domain.model.Supplier;
import com.warehouse.supplier.domain.port.secondary.SupplierServicePort;
import com.warehouse.supplier.domain.service.SupplierCodeGeneratorService;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class SupplyPortImpl implements SupplyPort {

    private final SupplierServicePort servicePort;

    private final SupplierCodeGeneratorService generatorService;

    @Override
    public void createSupplier(Supplier supplier) {
        final String supplierCode = generatorService.generate();
        supplier.setSupplierCode(supplierCode);
        servicePort.create(supplier);
    }

    @Override
    public List<Supplier> findAllSuppliers() {
        return servicePort.findAll();
    }

    @Override
    public void createMultipleSuppliers(List<Supplier> suppliers) {
        suppliers.forEach(supplier -> {
            final String supplierCode = generatorService.generate();
            supplier.setSupplierCode(supplierCode);
        });
        servicePort.createMultipleSuppliers(suppliers);
    }

    @Override
    public List<Supplier> findSuppliersByDepotCode(String depotCode) {
        return servicePort.findSuppliersByDepotCode(depotCode);
    }

    @Override
    public Supplier findSupplierByCode(String supplierCode) {
        return servicePort.findSupplierByCode(supplierCode);
    }
}
