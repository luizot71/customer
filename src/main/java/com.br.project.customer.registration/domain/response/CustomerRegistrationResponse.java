package com.br.project.customer.registration.domain.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class CustomerRegistrationResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    private long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("cpf")
    private String cpf;

    @JsonProperty("address")
    private String address;

    public CustomerRegistrationResponse() {
    }

    public CustomerRegistrationResponse(long id, String name, String cpf, String address) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
