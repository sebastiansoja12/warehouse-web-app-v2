package com.warehouse.supplier.configuration;

import com.warehouse.supplier.SupplyService;
import com.warehouse.supplier.domain.port.primary.SupplyPort;
import com.warehouse.supplier.domain.port.primary.SupplyPortImpl;
import com.warehouse.supplier.domain.port.secondary.SupplierRepository;
import com.warehouse.supplier.domain.port.secondary.SupplierServicePort;
import com.warehouse.supplier.domain.service.SupplierCodeGeneratorService;
import com.warehouse.supplier.domain.service.SupplierCodeGeneratorServiceImpl;
import com.warehouse.supplier.infrastructure.adapter.primary.SupplyServiceAdapter;
import com.warehouse.supplier.infrastructure.adapter.primary.mapper.SupplierRequestMapper;
import com.warehouse.supplier.infrastructure.adapter.primary.mapper.SupplierResponseMapper;
import com.warehouse.supplier.infrastructure.adapter.secondary.SupplierAdapter;
import com.warehouse.supplier.infrastructure.adapter.secondary.SupplierReadRepository;
import com.warehouse.supplier.infrastructure.adapter.secondary.SupplierRepositoryImpl;
import com.warehouse.supplier.infrastructure.adapter.secondary.mapper.DepotEntityMapper;
import com.warehouse.supplier.infrastructure.adapter.secondary.mapper.SupplierEntityMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SupplierConfiguration {

    @Bean
    public SupplyService supplyService(SupplyPort supplyPort) {
        final SupplierRequestMapper requestMapper = Mappers.getMapper(SupplierRequestMapper.class);
        final SupplierResponseMapper responseMapper = Mappers.getMapper(SupplierResponseMapper.class);
        return new SupplyServiceAdapter( requestMapper, responseMapper, supplyPort);
    }

    @Bean
    public SupplyPort supplyPort(SupplierServicePort servicePort, SupplierCodeGeneratorService generatorService) {
        return new SupplyPortImpl(servicePort, generatorService);
    }

    @Bean
    public SupplierCodeGeneratorService generatorService() {
        return new SupplierCodeGeneratorServiceImpl();
    }

    @Bean
    public SupplierServicePort servicePort(SupplierRepository supplierRepository) {
        return new SupplierAdapter(supplierRepository);
    }

    @Bean
    public SupplierRepository supplierRepository(SupplierReadRepository supplierReadRepository) {
        final SupplierEntityMapper supplierEntityMapper = Mappers.getMapper(SupplierEntityMapper.class);
        final DepotEntityMapper depotEntityMapper = Mappers.getMapper(DepotEntityMapper.class);
        return new SupplierRepositoryImpl(supplierEntityMapper, depotEntityMapper, supplierReadRepository);
    }
}
