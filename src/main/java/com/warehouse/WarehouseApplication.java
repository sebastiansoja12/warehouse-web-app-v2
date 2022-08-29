package com.warehouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.Async;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@Async
@EnableSwagger2
@EnableConfigurationProperties
@EntityScan(basePackages = "com.warehouse.*")
@EnableJpaRepositories(basePackages = "com.warehouse.*")
@ConfigurationPropertiesScan("com.warehouse.*")
public class WarehouseApplication {

	public static void main(String[] args) {
		SpringApplication.run(WarehouseApplication.class, args);
	}

}
