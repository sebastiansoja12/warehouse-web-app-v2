package com.warehouse.paypal.configuration;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = { "com.warehouse.paypal" })
@EntityScan(basePackages = { "com.warehouse.paypal" })
@EnableJpaRepositories(basePackages = { "com.warehouse.paypal" })
public class PaypalTestConfiguration {

}