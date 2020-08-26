package com.br.project.customer.registration.handler;

import com.br.project.customer.registration.handler.exceptions.ErrorCode;
import com.br.project.customer.registration.handler.exceptions.HttpCode;

public enum CustomerRegistrationErrorCode implements ErrorCode {

    errorProcessingInternalGetCustomerRegistration(
            1_001, HttpCode.INTERNAL_SERVER_ERROR, "error.get.registration"),
    errorProcessingGetValuesFromApi(1_002, HttpCode.INTERNAL_SERVER_ERROR, "error.get.from.api"),
    errorProcessingGetValuesFromAtomic(1_003, HttpCode.INTERNAL_SERVER_ERROR, "error.get.from.atomic"),
    errorInternalServerError(1_010, HttpCode.INTERNAL_SERVER_ERROR, "error.internal.error");


    private final Properties properties;

    CustomerRegistrationErrorCode(int errorCode, HttpCode httpCode, String message) {
        this.properties = Properties.of(errorCode, httpCode, this.name(), message);
    }

    public static CustomerRegistrationErrorCode getByString(String code) {
        code = code.replace("_", "");
        switch (code) {
            case "1_001":
                return errorProcessingInternalGetCustomerRegistration;
            default:
                return errorInternalServerError;
        }
    }

    @Override
    public ErrorCode.Properties getProperties() {
        return properties;
    }
}
