package com.claudio.coparmex.models.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @ManyToOne(
            optional = true,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "idEvent")
    @JsonIgnoreProperties(value = "partner")

    private Event event;


    public Partner() {
    }


    public Partner(Integer id, String name, String lastname, String phone, String email, String company, Address address) {
        super(id, name, lastname, phone, email);
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

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
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
                ", event=" + event +
                '}';
    }
}
