package com.warehouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.Async;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Async
@EnableSwagger2
@EntityScan(basePackages = {"com.warehouse.*"})
@ComponentScan(basePackages = {"com.warehouse.*"})
@SpringBootApplication(scanBasePackages = "com.warehouse.*")
@EnableJpaRepositories
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
