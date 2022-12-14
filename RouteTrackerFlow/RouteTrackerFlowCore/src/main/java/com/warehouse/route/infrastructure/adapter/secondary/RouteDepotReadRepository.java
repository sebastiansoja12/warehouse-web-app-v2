package com.warehouse.route.infrastructure.adapter.secondary;

import com.warehouse.route.infrastructure.adapter.secondary.entity.DepotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RouteDepotReadRepository extends JpaRepository<DepotEntity, Long> {

    Optional<DepotEntity> findByDepotCode(String depotCode);
}
