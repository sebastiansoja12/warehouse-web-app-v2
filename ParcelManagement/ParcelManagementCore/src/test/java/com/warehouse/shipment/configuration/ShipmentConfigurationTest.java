package com.warehouse.shipment.configuration;

import com.warehouse.paypal.domain.port.primary.PaypalPort;
import com.warehouse.shipment.infrastructure.adapter.secondary.ShipmentController;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ComponentScan(basePackages = { "com.warehouse.shipment", "com.warehouse.mail" })
@EntityScan(basePackages = { "com.warehouse.shipment", "com.warehouse.mail" })
@EnableJpaRepositories(basePackages = { "com.warehouse.shipment", "com.warehouse.mail" })
@Configuration
public class ShipmentConfigurationTest {

    @MockBean
    public JavaMailSender javaMailSender;

    @MockBean
    public PaypalPort paypalPort;

    @MockBean
    public ShipmentController shipmentController;

}
