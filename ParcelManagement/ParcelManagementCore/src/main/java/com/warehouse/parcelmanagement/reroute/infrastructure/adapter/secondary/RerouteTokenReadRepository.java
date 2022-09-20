package com.warehouse.parcelmanagement.reroute.infrastructure.adapter.secondary;

import com.warehouse.parcelmanagement.reroute.infrastructure.adapter.entity.RerouteTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RerouteTokenReadRepository extends JpaRepository<RerouteTokenEntity, Long> {

    @Query("SELECT entity FROM reroute.RerouteTokenEntity entity" +
           " WHERE entity.parcelId=:parcelId and entity.token=:token")
    List<RerouteTokenEntity> loadByTokenAndParcelId(
       @Param("token") Integer token,
       @Param("parcelId") Long parcelId
    );

    Optional<RerouteTokenEntity> findByToken(Integer token);

}
