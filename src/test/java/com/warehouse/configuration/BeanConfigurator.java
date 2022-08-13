package com.warehouse.configuration;


import com.warehouse.controller.ParcelController;
import com.warehouse.repository.ParcelRepository;
import com.warehouse.repository.RerouteTokenRepository;
import com.warehouse.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class BeanConfigurator {


    @Autowired
    private MailService mailService;

    @Autowired
    private ParcelRepository parcelRepository;

    @Autowired
    private ParcelExportService parcelExportService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private RerouteService rerouteService;

    @Autowired
    private RerouteTokenRepository rerouteTokenRepository;

    @Bean
    public ParcelController parcelController() {
        return new ParcelController(new ParcelService(parcelRepository, mailService, parcelExportService,
                paymentService, rerouteTokenRepository, rerouteService));

    }

}
