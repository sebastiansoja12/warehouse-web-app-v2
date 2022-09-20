package com.warehouse.parcelmanagement.reroute.infrastructure.adapter.secondary;

import com.warehouse.parcelmanagement.reroute.infrastructure.adapter.entity.ParcelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ParcelReadRepository extends JpaRepository<ParcelEntity, Long> {

    @Query("SELECT entity FROM reroute.ParcelEntity entity" +
            " WHERE entity.id=:parcelId")
    ParcelEntity loadByParcelId(@Param("parcelId") Long parcelId);
}
