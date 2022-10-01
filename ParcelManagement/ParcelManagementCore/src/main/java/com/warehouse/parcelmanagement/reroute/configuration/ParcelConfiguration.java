package com.warehouse.parcelmanagement.reroute.configuration;


import com.warehouse.parcelmanagement.reroute.domain.port.secondary.ParcelRepository;
import com.warehouse.parcelmanagement.reroute.infrastructure.adapter.secondary.ParcelReadRepository;
import com.warehouse.parcelmanagement.reroute.infrastructure.adapter.secondary.ParcelRepositoryImpl;
import com.warehouse.parcelmanagement.reroute.infrastructure.adapter.secondary.mapper.ParcelMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ParcelConfiguration {

    @Bean("rerouteToken.parcelRepository")
    public ParcelRepository parcelRepository(ParcelReadRepository repository) {
        final ParcelMapper parcelMapper = Mappers.getMapper(ParcelMapper.class);
        return new ParcelRepositoryImpl(parcelMapper, repository);
    }

}
