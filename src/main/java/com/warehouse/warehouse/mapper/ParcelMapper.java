package com.warehouse.warehouse.mapper;


import com.warehouse.warehouse.dto.ParcelDto;
import com.warehouse.warehouse.model.Parcel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ParcelMapper {

    @Mapping(target = "customer.id", source = "customerId")
    @Mapping(target = "recipient.id", source = "recipientId")
    Parcel map(ParcelDto parcel);
}
