package com.warehouse.parcelmanagement.reroute.infrastructure.adapter.secondary;

import com.warehouse.parcelmanagement.reroute.infrastructure.adapter.entity.RerouteTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RerouteTokenReadRepository extends JpaRepository<RerouteTokenEntity, Long> {

    @Query("SELECT entity FROM RerouteToken entity WHERE entity.parcelId=:parcelId and entity.token=:token")
    List<RerouteTokenEntity> loadRerouteTokenByParcelIdAndToken(
       @Param("parcelId") String parcelId,
       @Param("token") Integer token
    );

    Optional<RerouteTokenEntity> findByToken(Integer token);

}
