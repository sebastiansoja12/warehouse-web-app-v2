package com.warehouse.mapper;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;

public class MapperInitializer {

    @Bean
    public ParcelMapper parcelMapper() {
        return Mappers.getMapper(ParcelMapper.class);
    }
}
