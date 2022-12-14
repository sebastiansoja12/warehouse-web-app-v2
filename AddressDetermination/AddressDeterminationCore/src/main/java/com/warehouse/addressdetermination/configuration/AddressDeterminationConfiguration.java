package com.warehouse.addressdetermination.configuration;

import com.warehouse.addressdetermination.AddressDeterminationService;
import com.warehouse.addressdetermination.domain.port.primary.AddressDeterminationPort;
import com.warehouse.addressdetermination.domain.port.primary.AddressDeterminationPortImpl;
import com.warehouse.addressdetermination.domain.port.secondary.AddressDeterminationServicePort;
import com.warehouse.addressdetermination.domain.service.ComputeService;
import com.warehouse.addressdetermination.domain.service.ComputeServiceImpl;
import com.warehouse.addressdetermination.domain.service.UrlJsonReaderService;
import com.warehouse.addressdetermination.domain.service.UrlReaderServiceImpl;
import com.warehouse.addressdetermination.infrastructure.primary.AddressDeterminationServiceAdapter;
import com.warehouse.addressdetermination.infrastructure.secondary.AddressDeterminationAdapter;
import com.warehouse.depot.api.DepotService;
import com.warehouse.positionstack.configuration.TokenStageProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AddressDeterminationConfiguration {

    @Bean
    public AddressDeterminationService addressDeterminationService(AddressDeterminationPort addressDeterminationPort) {
        return new AddressDeterminationServiceAdapter(addressDeterminationPort);
    }

    @Bean
    public AddressDeterminationPort addressDeterminationPort(AddressDeterminationServicePort determinationServicePort) {
        return new AddressDeterminationPortImpl(determinationServicePort);
    }

    @Bean
    public AddressDeterminationServicePort addressDeterminationServicePort(TokenStageProperties tokenStageProperties,
        DepotService depotService, ComputeService computeService, UrlJsonReaderService urlJsonReaderService) {
        return new AddressDeterminationAdapter(tokenStageProperties, depotService, computeService, urlJsonReaderService);
    }

    @Bean
    public UrlJsonReaderService urlJsonReaderService() {
        return new UrlReaderServiceImpl();
    }

    @Bean
    public ComputeService computeService() {
        return new ComputeServiceImpl();
    }
}
