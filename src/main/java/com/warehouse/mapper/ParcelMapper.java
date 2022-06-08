package com.warehouse.mapper;


import com.warehouse.dto.ParcelDto;
import com.warehouse.model.Parcel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ParcelMapper {

    @Mapping(target = "id",  ignore = true)
    Parcel map(ParcelDto parcel);
}
