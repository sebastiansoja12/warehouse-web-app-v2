package com.warehouse.config;

import com.paypal.base.rest.APIContext;
import com.warehouse.repository.ParcelRepository;
import com.warehouse.repository.PaymentRepository;
import com.warehouse.repository.TemporaryRerouteTokenRepository;
import com.warehouse.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.TemplateEngine;

@Configuration
public class ServiceConfiguration {



    @Bean
    public MailContentBuilder mailContentBuilder() {
        return new MailContentBuilder();
    }

    @Bean
    public TemplateEngine templateEngine() {
       return new TemplateEngine();
    }

    @Bean
    public TemporaryReroute temporaryReroute( TemporaryRerouteTokenRepository temporaryRerouteTokenRepository,
                                              ParcelRepository parcelRepository,
                                              MailService mailService,
                                              ApplicationUrlConfig url) {
        return new TemporaryReroute(temporaryRerouteTokenRepository, parcelRepository, mailService, url);
    }

}
