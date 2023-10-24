package com.spring.firstSpring.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.firstSpring.validation.IPN;
import jakarta.validation.constraints.Size;

public class PersonDTO {

    @JsonIgnore
    private Long id;

    @IPN
    private String ipn;

    @Size(max = 10, message = "Name length must not be longer then 10 symbols")
    private String name;

    private String lastName;

    public PersonDTO(Long id, String ipn, String name, String lastName) {
        this.id = id;
        this.ipn = ipn;
        this.name = name;
        this.lastName = lastName;
    }

    public PersonDTO() {
    }

    public String getIpn() {
        return ipn;
    }

    public void setIpn(String ipn) {
        this.ipn = ipn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
