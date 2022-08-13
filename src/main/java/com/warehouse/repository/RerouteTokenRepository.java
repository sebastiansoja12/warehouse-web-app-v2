package com.warehouse.repository;

import com.warehouse.entity.Parcel;
import com.warehouse.entity.RerouteToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RerouteTokenRepository extends JpaRepository<RerouteToken, Long> {
    Optional<RerouteToken> findByToken(Integer token);
    Optional<RerouteToken> findByParcelId(UUID parcel_id);
    void deleteByToken(Integer token);
}
