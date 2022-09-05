package com.warehouse.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:application-url.properties")
public class ApplicationUrlConfig {

    @Value("${spring.localhost.url}")
    public String springUrl;

    @Value("${gui.localhost.url}")
    public String guiUrl;
}
