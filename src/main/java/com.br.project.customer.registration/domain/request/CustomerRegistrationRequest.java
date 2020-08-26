package com.br.project.customer.registration.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class CustomerRegistrationRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("name")
    private String name;

    @JsonProperty("cpf")
    private String cpf;

    @JsonProperty("address")
    private String address;

    public CustomerRegistrationRequest() {
    }

    public CustomerRegistrationRequest(String name, String cpf, String address) {
        this.name = name;
        this.cpf = cpf;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setNome(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
