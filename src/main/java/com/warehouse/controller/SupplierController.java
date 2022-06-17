package com.warehouse.controller;

import com.warehouse.service.SupplierService;
import com.warehouse.dto.SupplierDto;
import com.warehouse.entity.Supplier;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/suppliers")
public class SupplierController {

    private final SupplierService supplierService;

    @GetMapping()
    public List<Supplier> getAll() {
        return supplierService.findAll();
    }

    @GetMapping("/{supplierCode}")
    public Supplier findSupplierBySupplierCode(@PathVariable String supplierCode) {
        return supplierService.findBySupplierCode(supplierCode);
    }

    @PostMapping()
    public Supplier saveSupplier(@RequestBody SupplierDto supplier){
        return supplierService.save(supplier);
    }

    @PostMapping("/multiple")
    public List<Supplier> saveMultipleySuppliers(@RequestBody List<SupplierDto> supplier){
        return supplierService.saveMultipleSuppliers(supplier);
    }
    @DeleteMapping("/{supplierCode}")
    public void deleteSupplier(@PathVariable String supplierCode){
        supplierService.delete(supplierCode);
    }

}
