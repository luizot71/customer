package com.br.project.customer.registration.client;

import com.br.project.customer.registration.domain.response.CustomerRegistrationResponse;
import com.br.project.customer.registration.handler.CustomerRegistrationErrorCode;
import com.br.project.customer.registration.handler.exceptions.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CustomerRegistrationClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerRegistrationClient.class);

    @Autowired
    private RestTemplate restTemplate;

    @Value("${client.registration-atomic.url}")
    private String urlAtomic;

    @Value("${client.registration-api.url}")
    private String urlApi;

    @Value("${client.registration-api.uri.api}")
    private String uriApi;

    @Value("${client.registration-atomic.uri.customer}")
    private String uriCustomerRegistration;

    public List<CustomerRegistrationResponse> getRegistrationClientFromApi() {

        LOGGER.info("-----------------------GET CUSTOMERS FROM API-----------------------");

        URI uri = UriComponentsBuilder.fromUriString(urlApi + uriApi).build().toUri();

        List<CustomerRegistrationResponse> listResponse = Arrays.asList(restTemplate.getForObject(uri, CustomerRegistrationResponse[].class));

        LOGGER.info("Response of list Customers" + " " + listResponse.toString());

        return listResponse;
    }

    public @ResponseBody List<CustomerRegistrationResponse> saveCustomerRegistration(@RequestBody List<CustomerRegistrationResponse> request) {

        HttpEntity<Object> req = new HttpEntity<>(request);

        URI uri = UriComponentsBuilder.fromUriString(urlAtomic + uriCustomerRegistration).build().toUri();

        ParameterizedTypeReference<ArrayList<CustomerRegistrationResponse>> parameterizedTypeReference = new ParameterizedTypeReference<>() {};
        LOGGER.info(uri.toString());

        ResponseEntity<ArrayList<CustomerRegistrationResponse>> response = restTemplate.exchange(uri, HttpMethod.POST, req, parameterizedTypeReference);

        for (var id : request) {
            if (response.getStatusCode() != HttpStatus.CREATED) {
                for (var cd : response.getBody()) {
                    id.setId(cd.getId());
                    LOGGER.error(String.format("Error at communication with Atomic microservice - Id[%d]", id.getId()));
                }
                throw new BusinessException(CustomerRegistrationErrorCode.errorProcessingGetValuesFromAtomic);
            }
        }

        return response.getBody();
    }
}
