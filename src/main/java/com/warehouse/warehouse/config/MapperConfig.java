package com.warehouse.warehouse.config;

import com.warehouse.warehouse.mapper.PaymentMapper;
import com.warehouse.warehouse.mapper.SupplierMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public SupplierMapper mapper() {
        return Mappers.getMapper(SupplierMapper.class);
    }

    @Bean
    public PaymentMapper paymentMapper() {
        return Mappers.getMapper(PaymentMapper.class);
    }
}
