package com.warehouse.warehouse.controller;

import com.warehouse.warehouse.dto.SupplierDto;
import com.warehouse.warehouse.model.Supplier;
import com.warehouse.warehouse.service.SupplierService;
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

    @DeleteMapping("/{supplierCode}")
    public void deleteSupplier(@PathVariable String supplierCode){
        supplierService.delete(supplierCode);
    }

}
