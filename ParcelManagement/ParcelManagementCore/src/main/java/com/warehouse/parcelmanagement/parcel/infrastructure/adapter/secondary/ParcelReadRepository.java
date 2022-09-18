package com.warehouse.parcelmanagement.parcel.infrastructure.adapter.secondary;

import com.warehouse.parcelmanagement.parcel.infrastructure.adapter.entity.ParcelEntity;
import org.springframework.data.repository.Repository;

public interface ParcelReadRepository extends Repository<ParcelEntity, Long> {

}
