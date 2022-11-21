package com.warehouse.mail.configuration;

import com.warehouse.mail.domain.port.secondary.MailPort;
import com.warehouse.mail.domain.service.MailService;
import com.warehouse.mail.domain.service.MailServiceImpl;
import com.warehouse.mail.infrastructure.adapter.secondary.MailCreatorAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class JavaMailConfiguration {


    @Bean
    public MailService mailService(MailPort mailPort) {
        return new MailServiceImpl(mailPort);
    }

    @Bean
    public MailPort mailPort() {
        return new MailCreatorAdapter();
    }

    public JavaMailSender javaMailSender() {
        return new JavaMailSenderImpl();
    }
}
