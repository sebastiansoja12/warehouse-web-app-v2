package com.warehouse.parcelmanagement.reroute.configuration;

import com.warehouse.parcelmanagement.reroute.domain.port.primary.RerouteTokenPort;
import com.warehouse.parcelmanagement.reroute.domain.port.primary.RerouteTokenPortImpl;
import com.warehouse.parcelmanagement.reroute.domain.port.secondary.RerouteTokenRepository;
import com.warehouse.parcelmanagement.reroute.infrastructure.adapter.primary.mapper.RequestMapper;
import com.warehouse.parcelmanagement.reroute.infrastructure.adapter.primary.mapper.ResponseMapper;
import com.warehouse.parcelmanagement.reroute.infrastructure.adapter.secondary.RerouteTokenAdapter;
import com.warehouse.parcelmanagement.reroute.infrastructure.adapter.secondary.RerouteTokenReadRepository;
import com.warehouse.parcelmanagement.reroute.infrastructure.adapter.secondary.RerouteTokenRepositoryImpl;
import com.warehouse.parcelmanagement.reroute.infrastructure.adapter.secondary.mapper.RerouteTokenMapper;
import com.warehouse.parcelmanagement.reroute.infrastructure.api.RerouteService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class RerouteConfiguration {

    @Bean("rerouteToken.rerouteTokenRepository")
    public RerouteTokenRepository rerouteTokenRepository(RerouteTokenReadRepository repository) {
        final RerouteTokenMapper mapper = Mappers.getMapper(RerouteTokenMapper.class);
        return new RerouteTokenRepositoryImpl(mapper, repository);
    }

    @Bean
    public RerouteTokenPort rerouteTokenPort(
            @Qualifier("rerouteToken.rerouteTokenRepository") RerouteTokenRepository repository) {
        return new RerouteTokenPortImpl(repository);
    }

    @Bean
    public RerouteService rerouteTokenAdapter(RerouteTokenPort rerouteTokenPort) {
        final RequestMapper requestMapper = Mappers.getMapper(RequestMapper.class);
        final ResponseMapper responseMapper = Mappers.getMapper(ResponseMapper.class);

        return new RerouteTokenAdapter(requestMapper, responseMapper, rerouteTokenPort);
    }

    @Bean
    public JavaMailSender javaMailSender() {
        return new JavaMailSenderImpl();
    }
}
