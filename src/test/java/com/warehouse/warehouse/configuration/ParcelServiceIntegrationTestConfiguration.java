package com.warehouse.warehouse.configuration;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = {""})
@EntityScan(basePackages = {""})
@EnableJpaRepositories(basePackages = {""})
public class ParcelServiceIntegrationTestConfiguration {
}
