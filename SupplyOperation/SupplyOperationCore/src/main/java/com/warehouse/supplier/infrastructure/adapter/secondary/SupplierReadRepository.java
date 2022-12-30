package com.warehouse.supplier.infrastructure.adapter.secondary;

import com.warehouse.supplier.infrastructure.adapter.secondary.entity.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupplierReadRepository extends JpaRepository<SupplierEntity, Long> {

    List<SupplierEntity> findByDepot_DepotCode(String depotCode);

    Optional<SupplierEntity> findBySupplierCode(String supplierCode);
}

