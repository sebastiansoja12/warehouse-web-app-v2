package com.warehouse.qrcode.configuration;

import com.warehouse.qrcode.domain.service.*;
import com.warehouse.qrcode.infrastructure.adapter.primary.mapper.ParcelEntityMapper;
import com.warehouse.qrcode.infrastructure.adapter.primary.mapper.ParcelEntityMapperImpl;
import com.warehouse.shipment.domain.port.primary.ShipmentPort;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;

import java.awt.image.BufferedImage;

@Configuration
public class BarcodeGeneratorConfiguration {

    @Bean
    public HttpMessageConverter<BufferedImage> createImageHttpMessageConverter() {
        return new BufferedImageHttpMessageConverter();
    }

    @Bean
    public ParcelExportService parcelExportService(BarcodeGeneratorService generatorService) {
        final ParcelEntityMapper entityMapper = Mappers.getMapper(ParcelEntityMapper.class);
        return new ParcelExportServiceImpl(generatorService, entityMapper);
    }

    @Bean
    public BarcodeGeneratorService barcodeGeneratorService() {
        return new BarcodeGeneratorServiceImpl();
    }

    @Bean
    public ParcelEntityMapper parcelEntityMapper() {
        return new ParcelEntityMapperImpl();
    }

    @Bean(name="barcodeGeneratorParcelService")
    public ParcelService parcelService(ShipmentPort shipmentPort,
                                       ParcelExportService parcelExportService) {
        return new ParcelServiceImpl(shipmentPort, parcelExportService);
    }
}
