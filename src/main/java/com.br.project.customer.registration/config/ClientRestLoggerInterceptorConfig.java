package com.br.project.customer.registration.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class ClientRestLoggerInterceptorConfig implements ClientHttpRequestInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientRestLoggerInterceptorConfig.class);
    private static final String TITLE = "############################%s############################";
    private static final String SEPARATOR = "------------------------------------------------------";

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

        traceRequest(request, body);
        final var initTime = System.currentTimeMillis();
        final var response = execution.execute(request, body);
        final var totalTime = System.currentTimeMillis() - initTime;
        LOGGER.info(String.format(TITLE, "CHECK TIME INIT"));
        LOGGER.info(String.format("TIME::%s | URL::%s", totalTime, request.getURI().toString()));
        LOGGER.info(String.format(TITLE, "CHECK TIME END"));
        traceResponse(response);
        return response;
    }

    private void traceRequest(HttpRequest request, byte[] body) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.info(String.format(SEPARATOR, "request init"));
            LOGGER.info("URI            : {}", request.getURI());
            LOGGER.info("Method         : {}", request.getMethod());
            LOGGER.info("Headers        : {}", request.getHeaders());
            LOGGER.info("Request body   : {}", new String(body, StandardCharsets.UTF_8));
            LOGGER.info(String.format(SEPARATOR, "request end"));
        }
    }

    private void traceResponse(ClientHttpResponse response) throws IOException {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.info(String.format(SEPARATOR, "request init"));
            LOGGER.info("Status code            : {}", response.getStatusCode());
            LOGGER.info("Status text            : {}", response.getStatusText());
            LOGGER.info("Headers            : {}", response.getHeaders());
            LOGGER.info("Response body: {}",
                    StreamUtils.copyToString(response.getBody(), Charset.defaultCharset()));
            LOGGER.info(String.format(SEPARATOR, "response end"));
        }
    }
}
