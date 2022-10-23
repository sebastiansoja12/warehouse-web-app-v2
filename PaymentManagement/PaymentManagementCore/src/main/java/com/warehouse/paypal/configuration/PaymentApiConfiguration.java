package com.warehouse.paypal.configuration;

import com.warehouse.paypal.PaymentService;
import com.warehouse.paypal.domain.port.primary.PaypalPort;
import com.warehouse.paypal.infrastructure.adapter.primary.PaypalServiceAdapter;
import com.warehouse.paypal.infrastructure.adapter.primary.mapper.PrimaryRequestMapper;
import com.warehouse.paypal.infrastructure.adapter.primary.mapper.PrimaryResponseMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentApiConfiguration {

    @Bean(name = "apiPaymentService")
    public PaymentService paymentService(PaypalPort paypalPort) {
        final PrimaryRequestMapper requestMapper = Mappers.getMapper(PrimaryRequestMapper.class);
        final PrimaryResponseMapper responseMapper = Mappers.getMapper(PrimaryResponseMapper.class);
        return new PaypalServiceAdapter(requestMapper, responseMapper, paypalPort);
    }
}
