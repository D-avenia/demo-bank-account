package com.example.innotek.demobankchallenge.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class TemplateSettings {

    @Value("${api.sandbox.base_url_api:undefined}")
    private String baseUrlApi ;

    @Value("${api.sandbox.auth_schema:undefined}")
    private String authSchema ;

    @Value("${api.sandbox.api_key:undefined}")
    private String apiKey ;

    @Value("${api.sandbox.id_chiave:undefined}")
    private String IdChiave ;
}
