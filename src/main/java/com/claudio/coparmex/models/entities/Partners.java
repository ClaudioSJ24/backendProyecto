package com.claudio.coparmex.models.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Partners extends Person {
    private String company;
    private Address address;
    private LocalDateTime dischargeDate;
    private LocalDateTime updateDate;


    public Partners() {
    }

    public Partners(int idPerson, String name, String lastname, int phone, String email, String company, Address address) {
        super(idPerson, name, lastname, phone, email);
        this.company = company;
        this.address = address;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
