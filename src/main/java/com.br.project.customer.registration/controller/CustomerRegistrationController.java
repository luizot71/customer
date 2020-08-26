package com.br.project.customer.registration.controller;

import com.br.project.customer.registration.domain.response.CustomerRegistrationResponse;
import com.br.project.customer.registration.handler.CustomerRegistrationErrorCode;
import com.br.project.customer.registration.handler.exceptions.BusinessException;
import com.br.project.customer.registration.service.CustomerRegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1", produces = "application/json")
@Api(value = "Api for Registration Customer")
public class CustomerRegistrationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerRegistrationController.class);

    @Autowired private CustomerRegistrationService service;

    @GetMapping("/registration")
    @Cacheable(value = "registration")
    @ApiOperation(value = "Get values by Customer Registration database")
    public List<CustomerRegistrationResponse> getRegistrationValues() {
        LOGGER.info("Getting registration list from Atomic");

        try {
            return service.getCustomerRegistrationService();
        } catch (Exception e) {
            throw new BusinessException(CustomerRegistrationErrorCode.errorProcessingInternalGetCustomerRegistration);
        }
    }
}
