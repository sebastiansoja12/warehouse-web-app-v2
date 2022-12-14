package com.warehouse.positionstack.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Getter
@Configuration
@PropertySource("classpath:token.properties")
public class TokenStageProperties {

    private final static String POSITION_STACK_URL = "positionStackUrl";

    private final static String STAGE = "stage";

    private final static String TOKEN = "token";

    private final static String DIVIDER = "?";

    private final static String ACCESS_KEY = "access_key=";

    private final static String QUERY = "&query=";

    @Value("${token}")
    private String token;

    @Value("${stage}")
    private String stage;

    @Value("${position.stack.url}")
    private String positionStackUrl;

    public String createRequestLink(String request) {
        return positionStackUrl + DIVIDER + ACCESS_KEY + token + QUERY + request;
    }
}
