package com.br.project.customer.registration.service;

import com.br.project.customer.registration.domain.response.CustomerRegistrationResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

public interface CustomerRegistrationService {

    List<CustomerRegistrationResponse> getCustomerRegistrationService();

    @ResponseBody List<CustomerRegistrationResponse> saveCustomerRegistration(@RequestBody List<CustomerRegistrationResponse> request);
}
