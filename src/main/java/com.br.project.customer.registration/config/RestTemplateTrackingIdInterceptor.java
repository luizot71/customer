package com.br.project.customer.registration.config;

import org.slf4j.MDC;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.IOException;

public class RestTemplateTrackingIdInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

        MultiValueMap<String, String> values = new LinkedMultiValueMap<>();
        String trackingId = MDC.get("trackingId");
        String appKey = MDC.get("appkey");
        String appName = MDC.get("appName");
        values.add("trakingId", trackingId);
        values.add("appkey", appKey);
        values.add("appName", appName);
        request.getHeaders().addAll(values);
        return execution.execute(request, body);
    }
}
