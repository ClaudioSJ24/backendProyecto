package com.claudio.coparmex.models.entities;

import com.claudio.coparmex.models.entities.enumerators.City;


import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Address implements Serializable {

    private String street;
    private int number;
    private String colony;
    private int codePostal;
    private String city;

    public Address() {
    }

    public Address(String street, int number, String colony, int codePostal, String city) {
        this.street = street;
        this.number = number;
        this.colony = colony;
        this.codePostal = codePostal;
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getColony() {
        return colony;
    }

    public void setColony(String colony) {
        this.colony = colony;
    }

    public int getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(int codePostal) {
        this.codePostal = codePostal;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
