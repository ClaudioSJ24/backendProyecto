package com.claudio.coparmex.models.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "contacts")
@PrimaryKeyJoinColumn(name = "idPerson")
public class Contact extends Person{

    @Column(nullable = false, length = 50)
    private String company;
    @Column(nullable = false, length = 50)
    private String activity;
    @Column(nullable = false)
    private String message;
    @Column( name = "discharge_date")
    private LocalDateTime dischargeDate;

    public Contact (){}
    public Contact(Integer id, String name, String lastname, String phone, String email,String company, String activity, String message){
        super(id, name, lastname, phone, email);
        this.company = company;
        this.activity = activity;
        this.message = message;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @PrePersist
    public void beforePersistence(){
        this.dischargeDate= LocalDateTime.now();

    }

    @Override
    public String toString() {
        return super.toString()+
                "Contact{" +
                "company='" + company + '\'' +
                ", activity='" + activity + '\'' +
                ", message='" + message + '\'' +
                ", dischargeDate=" + dischargeDate +
                '}';
    }
}
