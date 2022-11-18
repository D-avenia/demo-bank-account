package com.example.innotek.demobankchallenge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import javax.net.ssl.SSLException;

@Configuration
public class ClientConfig {

    @Bean
    public WebClient webclient(ClientSettings settings) throws SSLException {

        return WebClient.builder()
                .baseUrl(settings.getBaseUrlApi())
                .defaultHeader("Auth-Schema", settings.getAuthSchema())
                .defaultHeader("apikey", settings.getApiKey())
                .build();
    }
}
