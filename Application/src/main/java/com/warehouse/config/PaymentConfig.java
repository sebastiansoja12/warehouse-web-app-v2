package com.warehouse.config;


import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;
import com.warehouse.entity.PaymentInformation;
import com.warehouse.repository.PaymentRepository;
import com.warehouse.service.ParcelExportService;
import com.warehouse.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@PropertySource("classpath:application.properties")
@PropertySource("classpath:application-url.properties")
public class PaymentConfig {


    @Value("${paypal.client.id}")
    private String clientId;

    @Value("${paypal.client.secret}")
    private String clientSecret;

    @Value("${paypal.mode}")
    private String mode;


    @Bean
    public Map<String, String> paypalSdkConfig(){
        Map<String, String> configMap = new HashMap<String, String>();
        configMap.put("mode", mode);
        return configMap;
    }

    @Bean
    public OAuthTokenCredential oAuthTokenCredential() {
        return new OAuthTokenCredential(clientId, clientSecret, paypalSdkConfig());
    }

    @Bean
    public APIContext apiContext() throws PayPalRESTException {
        APIContext context = new APIContext(oAuthTokenCredential().getAccessToken());
        context.setConfigurationMap(paypalSdkConfig());
        return context;
    }

    @Bean
    public PaymentInformation paymentInformation() {
        return new PaymentInformation();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public ApplicationUrlConfig applicationUrlConfig() {
        return new ApplicationUrlConfig();
    }

    @Bean(name = "parcelPaymentService")
    public PaymentService paymentService(APIContext apiContext,
                                         PaymentRepository paymentRepository,
                                         ApplicationUrlConfig config) {
        return new PaymentService(apiContext, paymentRepository, config);
    }

    @Bean
    public ParcelExportService parcelExportService() {
        return new ParcelExportService();
    }

}
