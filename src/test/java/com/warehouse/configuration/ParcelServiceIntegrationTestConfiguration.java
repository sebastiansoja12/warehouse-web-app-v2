package com.warehouse.configuration;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = {"com.warehouse.service"})
@EntityScan(basePackages = {"com.warehouse.entity"})
@EnableJpaRepositories(basePackages = {"com.warehouse.repository"})
public class ParcelServiceIntegrationTestConfiguration {
}
