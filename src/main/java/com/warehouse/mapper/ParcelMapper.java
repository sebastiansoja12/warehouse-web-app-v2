package com.warehouse.mapper;


import com.warehouse.dto.ParcelDto;
import com.warehouse.model.Parcel;
import org.mapstruct.Mapper;

@Mapper
public interface ParcelMapper {

    Parcel map(ParcelDto parcel);
}
