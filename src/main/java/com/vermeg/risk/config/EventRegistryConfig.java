package com.vermeg.risk.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class EventRegistryConfig {

    @Value("${eventregistry.apiKey}")
    private String apiKey;

    @Bean
    public RestTemplate eventRegistryRestTemplate() {
        return new RestTemplate();
    }
}
