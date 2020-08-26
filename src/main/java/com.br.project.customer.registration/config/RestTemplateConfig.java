package com.br.project.customer.registration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {
        final var restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
        final List<ClientHttpRequestInterceptor> interceptors = new ArrayList();
        interceptors.add(new ClientRestLoggerInterceptorConfig());
        interceptors.add(new RestTemplateTrackingIdInterceptor());
        restTemplate.setInterceptors(interceptors);
        return restTemplate;
    }
}
