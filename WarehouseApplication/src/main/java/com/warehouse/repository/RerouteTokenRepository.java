package com.warehouse.repository;

import com.warehouse.entity.RerouteToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RerouteTokenRepository extends JpaRepository<RerouteToken, Long> {
    Optional<RerouteToken> findByToken(Integer token);

    Optional<RerouteToken> findByParcelId(Long parcel_id);

    List<RerouteToken> findByParcelIdAndToken(Long parcel_id, Integer token);

    void deleteByToken(Integer token);

    @Modifying
    @Query("delete from warehouseapplication.RerouteTokenEntity t where t.expiryDate <= ?1")
    void deleteAllExpiredSince(Instant now);
}
