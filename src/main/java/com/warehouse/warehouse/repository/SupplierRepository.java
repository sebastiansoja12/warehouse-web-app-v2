package com.warehouse.warehouse.repository;

import com.warehouse.warehouse.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    Supplier findBySupplierCode(String supplierCode);
}
