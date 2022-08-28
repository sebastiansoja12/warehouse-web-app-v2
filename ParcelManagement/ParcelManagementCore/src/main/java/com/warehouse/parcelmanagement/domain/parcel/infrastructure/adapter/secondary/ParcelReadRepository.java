package com.warehouse.parcelmanagement.domain.parcel.infrastructure.adapter.secondary;

import com.warehouse.parcelmanagement.domain.parcel.infrastructure.adapter.entity.ParcelEntity;
import org.springframework.data.repository.Repository;

public interface ParcelReadRepository extends Repository<ParcelEntity, Long> {

}
