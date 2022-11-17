package com.example.innotek.demobankchallenge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;

@Configuration
public class ClientConfig {

    @Bean
    public WebClient webclient(ClientSettings settings) {

        return WebClient.builder()
                .baseUrl(settings.getBaseUrlApi())
                .defaultCookie("cookieKey", "cookieValue")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url", settings.getBaseUrlApi()))
                .build();
    }
}
