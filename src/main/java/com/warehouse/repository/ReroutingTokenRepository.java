package com.warehouse.repository;

import com.warehouse.entity.ReroutingToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReroutingTokenRepository extends JpaRepository<ReroutingToken, Long> {
    Optional<ReroutingToken> findByToken(Integer token);

    void deleteByToken(Integer token);
}
