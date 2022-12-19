package com.warehouse.addressdetermination.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = { "com.warehouse.addressdetermination" })
@EntityScan(basePackages = { "com.warehouse.addressdetermination" })
@EnableJpaRepositories(basePackages = { "com.warehouse.addressdetermination" })
public class AddressDeterminationTestConfiguration {
}
