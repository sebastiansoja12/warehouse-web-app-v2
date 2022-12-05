package com.warehouse.route.infrastructure.adapter.secondary;

import com.warehouse.route.infrastructure.adapter.secondary.entity.RouteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Repository
public interface RouteReadRepository extends JpaRepository<RouteEntity, UUID> {

    List<RouteEntity> findByParcelId(Long parcelId);

    List<RouteEntity> findAllByUserUsername(String username);

    @Transactional
    void deleteByParcelIdAndDepot_DepotCodeAndUser_Username(Long parcelId, String depotCode, String username);
}
