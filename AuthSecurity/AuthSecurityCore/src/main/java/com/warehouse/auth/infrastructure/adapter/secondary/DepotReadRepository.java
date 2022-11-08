package com.warehouse.auth.infrastructure.adapter.secondary;

import com.warehouse.auth.infrastructure.adapter.secondary.entity.DepotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepotReadRepository extends JpaRepository<DepotEntity, Long> {

    DepotEntity findByDepotCode(String depotCode);
}
