package com.claudio.coparmex.models.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "partners")
@PrimaryKeyJoinColumn(name = "idPerson")

public class Partner extends Person {



    @Column(nullable = false, length = 40)
    private String company;
    @Embedded
    private Address address;
    @Column( name = "discharge_date")
    private LocalDateTime dischargeDate;
    @Column(name = "update_date")
    private LocalDateTime updateDate;






    public Partner() {
    }



    public Partner(Integer id, String name, String lastname, String phone, String email,String company, Address address) {
        super(id, name, lastname, phone, email);
        this.company = company;
        this.address = address;

    }

    /*public Partner(Integer idP, String name, String lastname, String phone, String email, int passes,String password, Set<Rol> roles) {
        super(idP, name, lastname, phone, email, passes);
        this.password = password;
        this.roles = roles;
    }*/
   /* public Partner(Integer id, String name, String lastname, String phone, Strinmail,g e int passes,String password, String company, Address address,  String user, Set<Rol> roles) {
        super(id, name, lastname, phone, email,passes);
        this.password = password;
        this.company = company;
        this.address = address;
        this.user = user;
        this.roles = roles;
    }*/


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

    @PrePersist
    public void beforePersistence(){
        this.dischargeDate= LocalDateTime.now();

    }
    @PreUpdate
    public void afterPersistence(){
        this.updateDate = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return super.toString()+
                "Partner{" +
                "company='" + company + '\'' +
                ", address=" + address +

                ", dischargeDate=" + dischargeDate +
                ", updateDate=" + updateDate +

                '}';
    }
}
