package com.warehouse.parcelmanagement.reroute.configuration;

import com.warehouse.parcelmanagement.reroute.domain.port.primary.RerouteTokenPort;
import com.warehouse.parcelmanagement.reroute.domain.port.primary.RerouteTokenPortImpl;
import com.warehouse.parcelmanagement.reroute.domain.port.secondary.RerouteTokenRepository;
import com.warehouse.parcelmanagement.reroute.domain.service.RerouteServiceImpl;
import com.warehouse.parcelmanagement.reroute.infrastructure.adapter.primary.RerouteTokenServiceAdapter;
import com.warehouse.parcelmanagement.reroute.infrastructure.adapter.primary.mapper.PrimaryRequestMapper;
import com.warehouse.parcelmanagement.reroute.infrastructure.adapter.primary.mapper.PrimaryResponseMapper;
import com.warehouse.parcelmanagement.reroute.infrastructure.adapter.secondary.MailCreator;
import com.warehouse.parcelmanagement.reroute.infrastructure.adapter.secondary.RerouteTokenAdapter;
import com.warehouse.parcelmanagement.reroute.infrastructure.adapter.secondary.RerouteTokenReadRepository;
import com.warehouse.parcelmanagement.reroute.infrastructure.adapter.secondary.RerouteTokenRepositoryImpl;
import com.warehouse.parcelmanagement.reroute.infrastructure.adapter.secondary.mapper.RequestMapper;
import com.warehouse.parcelmanagement.reroute.infrastructure.adapter.secondary.mapper.RerouteTokenMapper;
import com.warehouse.parcelmanagement.reroute.infrastructure.adapter.secondary.mapper.ResponseMapper;
import com.warehouse.parcelmanagement.reroute.infrastructure.api.RerouteService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class RerouteConfiguration {


    @Bean
    RerouteTokenAdapter rerouteTokenServicePort(RerouteTokenReadRepository repository) {
        final RequestMapper requestMapper = Mappers.getMapper(RequestMapper.class);
        final MailCreator mailCreator = new MailCreator(javaMailSender());
        return new RerouteTokenAdapter(requestMapper, mailCreator, rerouteTokenRepository(repository));
    }
    @Bean("rerouteToken.rerouteTokenRepository")
    public RerouteTokenRepository rerouteTokenRepository(RerouteTokenReadRepository repository) {
        final RerouteTokenMapper mapper = Mappers.getMapper(RerouteTokenMapper.class);
        return new RerouteTokenRepositoryImpl(mapper, repository);
    }


    @Bean
    public RerouteTokenPort rerouteTokenPort(
            com.warehouse.parcelmanagement.reroute.domain.port.secondary.RerouteTokenPort rerouteTokenPort) {
        final com.warehouse.parcelmanagement.reroute.domain.service.RerouteService rerouteService =
                new RerouteServiceImpl(rerouteTokenPort);
        return new RerouteTokenPortImpl(rerouteService);
    }

    @Bean
    public RerouteService rerouteTokenAdapter(RerouteTokenPort rerouteTokenPort) {
        final PrimaryRequestMapper primaryRequestMapper = Mappers.getMapper(PrimaryRequestMapper.class);
        final PrimaryResponseMapper primaryResponseMapper = Mappers.getMapper(PrimaryResponseMapper.class);
        final MailCreator mailCreator = new MailCreator(javaMailSender());

        return new RerouteTokenServiceAdapter(rerouteTokenPort, primaryRequestMapper, primaryResponseMapper);
    }

    @Bean
    public com.warehouse.parcelmanagement.reroute.domain.service.RerouteService rerouteService(
            com.warehouse.parcelmanagement.reroute.domain.port.secondary.RerouteTokenPort rerouteTokenPort) {
        return new RerouteServiceImpl(rerouteTokenPort);
    }

    @Bean
    public JavaMailSender javaMailSender() {
        return new JavaMailSenderImpl();
    }
}
