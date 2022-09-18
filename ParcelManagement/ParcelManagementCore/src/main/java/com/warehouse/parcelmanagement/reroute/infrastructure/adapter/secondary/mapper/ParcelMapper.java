package com.warehouse.parcelmanagement.reroute.infrastructure.adapter.secondary.mapper;

import com.warehouse.parcelmanagement.reroute.domain.model.UpdateParcelRequest;
import com.warehouse.parcelmanagement.reroute.domain.vo.ParcelResponse;
import com.warehouse.parcelmanagement.reroute.infrastructure.adapter.entity.ParcelEntity;
import org.mapstruct.Mapper;

@Mapper
public interface ParcelMapper {

    ParcelResponse map(UpdateParcelRequest parcelRequest);

    ParcelResponse map(ParcelEntity parcel);
}
