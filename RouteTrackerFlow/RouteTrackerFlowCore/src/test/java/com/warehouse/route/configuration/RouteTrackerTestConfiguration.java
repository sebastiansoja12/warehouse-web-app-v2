package com.warehouse.route.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.warehouse.auth.domain.port.primary.AuthenticationPort;
import com.warehouse.depot.api.DepotService;
import org.mockito.Mock;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.web.servlet.MockMvc;

@ComponentScan(basePackages = { "com.warehouse.route", "com.warehouse.depot"})
@EntityScan(basePackages = { "com.warehouse.route", "com.warehouse.depot" })
@EnableJpaRepositories(basePackages = { "com.warehouse.route", "com.warehouse.depot"})
public class RouteTrackerTestConfiguration {

    @MockBean
    public DepotService depotService;

    @MockBean
    public AuthenticationPort authenticationPort;

}
