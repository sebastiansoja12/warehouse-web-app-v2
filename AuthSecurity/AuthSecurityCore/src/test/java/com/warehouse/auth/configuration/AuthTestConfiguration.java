package com.warehouse.auth.configuration;

import com.warehouse.depot.api.DepotService;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@ComponentScan(basePackages = { "com.warehouse.auth" })
@EntityScan(basePackages = { "com.warehouse.auth" })
@EnableJpaRepositories(basePackages = { "com.warehouse.auth" })
public class AuthTestConfiguration {

    @MockBean
    public DepotService depotService;

}
