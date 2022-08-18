package com.warehouse.configuration;


import com.warehouse.controller.ParcelController;
import com.warehouse.repository.ParcelRepository;
import com.warehouse.service.MailService;
import com.warehouse.service.ParcelExportService;
import com.warehouse.service.ParcelService;
import com.warehouse.service.PaymentService;
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

    @Bean
    public ParcelController parcelController() {
        return new ParcelController(new ParcelService(parcelRepository, mailService, parcelExportService,
                paymentService));

    }

}
