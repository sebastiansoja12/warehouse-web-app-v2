package com.warehouse.configuration;


import com.warehouse.service.MailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@PropertySource("classpath:application-test.properties")
public class BeanConfigurator {

    @Bean
    public MailService mailService() {
        return new MailService();
    }
}
