package com.warehouse.route.infrastructure.adapter.secondary;

import com.warehouse.route.infrastructure.adapter.secondary.entity.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupplierReadRepository extends JpaRepository<SupplierEntity, Long> {
    Optional<SupplierEntity> findBySupplierCode(String supplierCode);
}
