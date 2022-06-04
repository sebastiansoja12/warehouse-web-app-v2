package com.warehouse.warehouse.mapper;


import com.warehouse.warehouse.dto.ParcelDto;
import com.warehouse.warehouse.model.Parcel;
import org.mapstruct.Mapper;

@Mapper
public interface ParcelMapper {

    Parcel map(ParcelDto parcel);
}
