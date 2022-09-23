package com.warehouse.config;

import com.warehouse.exceptions.JsonExceptionHandler;
import com.warehouse.repository.ParcelRepository;
import com.warehouse.repository.RerouteTokenRepository;
import com.warehouse.service.MailContentBuilder;
import com.warehouse.service.MailService;
import com.warehouse.service.RerouteService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;
import org.thymeleaf.TemplateEngine;

@Configuration
public class ServiceConfiguration {

    @Bean(name = "temporaryRerouteService")
    public RerouteService rerouteService( RerouteTokenRepository rerouteTokenRepository,
                                          ParcelRepository parcelRepository,
                                          MailService mailService,
                                          ApplicationUrlConfig url) {
        return new RerouteService(rerouteTokenRepository, parcelRepository, mailService, url);
    }

    @Bean
    public MailContentBuilder mailContentBuilder() {
        return new MailContentBuilder();
    }

    @Bean
    public TemplateEngine templateEngine() {
       return new TemplateEngine();
    }

}
