package com.claudio.coparmex.security.dto;

import com.claudio.coparmex.models.entities.Address;
import com.sun.istack.NotNull;

import java.util.HashSet;
import java.util.Set;

public class NewPersonDto {

    private String name;
    private String lastname;

    private String phone;
    private String email;
    private int passes;
    private String password;
    private String company;
    private Address address;
    private String user;


    private Set<String> roles = new HashSet<>();

    /**
     * {
     *     "class": "partner",
     *
     *         "name": "Claudio",
     *         "lastname": "anastacia Mendoza",
     *         "phone": "345678965",
     *         "email": "padrino@ljdfkh",
     *         "passes": 4,
     *         "password":"12345",
     *         "company": "Sony",
     *         "address": {
     *             "street": "4 norte",
     *             "number": 24,
     *             "colony": "la orizabeña",
     *             "codePostal": 78654,
     *             "city": "Puebla"
     *         },
     *         "user": "que pasa hay",
     *         "roles": ["administrator"] -->Campo opcional
     *
     * }
     *
     */

    /*public NewPersonDto(String name, String user, String email, String password, Set<String> roles) {
        this.name = name;
        this.user = user;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPasses() {
        return passes;
    }

    public void setPasses(int passes) {
        this.passes = passes;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
