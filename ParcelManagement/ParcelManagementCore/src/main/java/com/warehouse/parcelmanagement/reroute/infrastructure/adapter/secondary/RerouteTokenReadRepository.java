package com.warehouse.parcelmanagement.reroute.infrastructure.adapter.secondary;

import com.warehouse.parcelmanagement.reroute.infrastructure.adapter.entity.RerouteTokenEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RerouteTokenReadRepository extends Repository<RerouteTokenEntity, Long> {

    @Query(
            "select RerouteTokenEntity from RerouteToken " +
                    "where parcelId=:parcelId " +
                    "and token=:token"
    )
    Optional<RerouteTokenEntity> loadRerouteTokenByParcelIdAndToken(
       @Param("parcelId") String parcelId,
       @Param("token") Integer token
    );

}
