package com.example.innotek.demobankchallenge.config;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import javax.net.ssl.SSLException;
import java.util.Collections;

@Configuration
public class ClientConfig {

    @Bean
    public WebClient webclient(ClientSettings settings) throws SSLException {

        SslContext sslContext = SslContextBuilder
                .forClient()
                .trustManager(InsecureTrustManagerFactory.INSTANCE)
                .build();

        HttpClient httpConnector = HttpClient
                .create()
                .secure(t -> t.sslContext(sslContext));

        return WebClient.builder()
                .baseUrl(settings.getBaseUrlApi())
                .defaultCookie("cookieKey", "cookieValue")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url", settings.getBaseUrlApi()))
                .clientConnector(new ReactorClientHttpConnector(httpConnector))
                .build();
    }
}
