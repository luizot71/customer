package com.br.project.customer.registration.service.impl;

import com.br.project.customer.registration.client.CustomerRegistrationClient;
import com.br.project.customer.registration.domain.response.CustomerRegistrationResponse;
import com.br.project.customer.registration.handler.CustomerRegistrationErrorCode;
import com.br.project.customer.registration.handler.exceptions.BusinessException;
import com.br.project.customer.registration.service.CustomerRegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpServerErrorException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerRegistrationServiceImpl implements CustomerRegistrationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerRegistrationServiceImpl.class);

    @Autowired
    private CustomerRegistrationClient customerRegistrationClient;

    public List<CustomerRegistrationResponse> getCustomerRegistrationService() {

        try {
            List<CustomerRegistrationResponse> values = customerRegistrationClient.getRegistrationClientFromApi();

            List<CustomerRegistrationResponse> response;
            if(!values.isEmpty()) {
                response = saveCustomerRegistration(values);
            }else if (values.isEmpty()) {
                response = new ArrayList<>();
            } else {
                response = values;
            }
            return response;
        } catch (HttpServerErrorException e) {
            LOGGER.warn(e.getMessage());
            throw new BusinessException(CustomerRegistrationErrorCode.errorInternalServerError);
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
            throw new BusinessException(CustomerRegistrationErrorCode.errorProcessingGetValuesFromApi);
        }
    }

    @Override
    public @ResponseBody List<CustomerRegistrationResponse> saveCustomerRegistration(@RequestBody List<CustomerRegistrationResponse> request) {

        try {
            return customerRegistrationClient.saveCustomerRegistration(request);
        } catch (HttpServerErrorException e) {
            LOGGER.warn(e.getMessage());
            throw new BusinessException(CustomerRegistrationErrorCode.errorInternalServerError);
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
            throw new BusinessException(CustomerRegistrationErrorCode.errorProcessingGetValuesFromAtomic);
        }
    }
}
