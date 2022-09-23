package com.warehouse.parcelmanagement.reroute.configuration;

import com.warehouse.mail.domain.service.MailService;
import com.warehouse.parcelmanagement.reroute.domain.port.primary.RerouteTokenPort;
import com.warehouse.parcelmanagement.reroute.domain.port.primary.RerouteTokenPortImpl;
import com.warehouse.parcelmanagement.reroute.domain.port.secondary.ParcelRepository;
import com.warehouse.parcelmanagement.reroute.domain.port.secondary.RerouteTokenRepository;
import com.warehouse.parcelmanagement.reroute.domain.service.RerouteServiceImpl;
import com.warehouse.parcelmanagement.reroute.infrastructure.adapter.primary.RerouteTokenServiceAdapter;
import com.warehouse.parcelmanagement.reroute.infrastructure.adapter.primary.mapper.PrimaryRequestMapper;
import com.warehouse.parcelmanagement.reroute.infrastructure.adapter.primary.mapper.PrimaryResponseMapper;
import com.warehouse.parcelmanagement.reroute.infrastructure.adapter.secondary.RerouteTokenAdapter;
import com.warehouse.parcelmanagement.reroute.infrastructure.adapter.secondary.RerouteTokenReadRepository;
import com.warehouse.parcelmanagement.reroute.infrastructure.adapter.secondary.RerouteTokenRepositoryImpl;
import com.warehouse.parcelmanagement.reroute.infrastructure.adapter.secondary.mapper.RequestMapper;
import com.warehouse.parcelmanagement.reroute.infrastructure.adapter.secondary.mapper.RequestMapperImpl;
import com.warehouse.parcelmanagement.reroute.infrastructure.adapter.secondary.mapper.RerouteTokenMapper;
import com.warehouse.parcelmanagement.reroute.infrastructure.api.RerouteService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RerouteConfiguration {


    @Bean
    public RerouteTokenAdapter rerouteTokenAdapter(RerouteTokenRepository rerouteTokenRepository,
                                                ParcelRepository parcelRepository,
                                                MailService mailService) {
        final RequestMapper requestMapper = Mappers.getMapper(RequestMapper.class);
        return new RerouteTokenAdapter(requestMapper, rerouteTokenRepository, parcelRepository, mailService);
    }

    @Bean(name="reroute.rerouteTokenRepository")
    public RerouteTokenRepository rerouteTokenRepository(RerouteTokenReadRepository repository) {
        final RerouteTokenMapper rerouteTokenMapper = Mappers.getMapper(RerouteTokenMapper.class);
        return new RerouteTokenRepositoryImpl(rerouteTokenMapper, repository);
    }


    @Bean
    public RerouteTokenPort rerouteTokenPort(
            com.warehouse.parcelmanagement.reroute.domain.port.secondary.RerouteTokenPort rerouteTokenPort) {
        final com.warehouse.parcelmanagement.reroute.domain.service.RerouteService rerouteService =
                new RerouteServiceImpl(rerouteTokenPort);
        return new RerouteTokenPortImpl(rerouteService);
    }

    @Bean(name = "apiRerouteService")
    public RerouteService rerouteService(RerouteTokenPort rerouteTokenPort) {
        final PrimaryRequestMapper primaryRequestMapper = Mappers.getMapper(PrimaryRequestMapper.class);
        final PrimaryResponseMapper primaryResponseMapper = Mappers.getMapper(PrimaryResponseMapper.class);

        return new RerouteTokenServiceAdapter(rerouteTokenPort, primaryRequestMapper, primaryResponseMapper);
    }

    @Bean
    public com.warehouse.parcelmanagement.reroute.domain.service.RerouteService rerouteService(
            com.warehouse.parcelmanagement.reroute.domain.port.secondary.RerouteTokenPort rerouteTokenPort) {
        return new RerouteServiceImpl(rerouteTokenPort);
    }

    @Bean
    public RequestMapper requestMapper() {
        return new RequestMapperImpl();
    }
}
