package com.warehouse.auth.infrastructure.adapter;

import com.warehouse.auth.infrastructure.adapter.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationReadRepository extends JpaRepository<UserEntity, Long> {

}
