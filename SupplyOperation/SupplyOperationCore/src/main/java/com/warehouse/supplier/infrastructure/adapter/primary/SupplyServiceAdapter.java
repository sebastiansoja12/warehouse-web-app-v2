package com.warehouse.supplier.infrastructure.adapter.primary;

import com.warehouse.supplier.SupplyService;
import com.warehouse.supplier.domain.model.Supplier;
import com.warehouse.supplier.domain.port.primary.SupplyPort;
import com.warehouse.supplier.dto.SupplierAddRequest;
import com.warehouse.supplier.dto.SupplierDto;
import com.warehouse.supplier.infrastructure.adapter.primary.mapper.SupplierRequestMapper;
import com.warehouse.supplier.infrastructure.adapter.primary.mapper.SupplierResponseMapper;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class SupplyServiceAdapter implements SupplyService {

    private final SupplierRequestMapper requestMapper;

    private final SupplierResponseMapper responseMapper;

    private final SupplyPort supplyPort;


    @Override
    public void createSupplier(SupplierAddRequest supplierAddRequest) {
        final Supplier supplier = requestMapper.map(supplierAddRequest);
        supplyPort.createSupplier(supplier);
    }

    @Override
    public void createMultipleSuppliers(List<SupplierAddRequest> supplierAddRequestList) {
        final List<Supplier> suppliers = requestMapper.map(supplierAddRequestList);
        supplyPort.createMultipleSuppliers(suppliers);
    }

    @Override
    public List<SupplierDto> findSuppliersByDepotCode(String depotCode) {
        final List<Supplier> suppliers = supplyPort.findSuppliersByDepotCode(depotCode);
        return responseMapper.map(suppliers);
    }

    @Override
    public SupplierDto findSupplierBySupplierCode(String supplierCode) {
        final Supplier supplier = supplyPort.findSupplierByCode(supplierCode);
        return responseMapper.map(supplier);
    }

    @Override
    public List<SupplierDto> findAllSuppliers() {
        final List<Supplier> suppliers = supplyPort.findAllSuppliers();
        return responseMapper.map(suppliers);
    }
}
