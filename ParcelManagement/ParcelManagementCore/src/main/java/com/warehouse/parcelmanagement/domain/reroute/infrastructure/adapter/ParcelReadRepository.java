package com.warehouse.parcelmanagement.domain.reroute.infrastructure.adapter;

import com.warehouse.parcelmanagement.domain.reroute.infrastructure.adapter.entity.ParcelEntity;
import org.springframework.data.repository.Repository;

public interface ParcelReadRepository extends Repository<ParcelEntity, Long> {

}
