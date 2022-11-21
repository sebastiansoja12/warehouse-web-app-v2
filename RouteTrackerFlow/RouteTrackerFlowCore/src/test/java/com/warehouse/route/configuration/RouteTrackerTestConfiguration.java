package com.warehouse.route.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = { "com.warehouse.route", "com.warehouse.auth"})
@EntityScan(basePackages = { "com.warehouse.route", "com.warehouse.auth" })
@EnableJpaRepositories(basePackages = { "com.warehouse.route", "com.warehouse.auth"})
public class RouteTrackerTestConfiguration {

}
