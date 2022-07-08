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

    @Column(nullable = false)
    private String user;
    @Column(nullable = false, unique = true)
    private String password;

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
    @JsonIgnoreProperties({
            "hibernateLazyInitializer","partner"})

    private Event event;


    @Column(nullable = false)
    @ManyToMany
    @JoinTable(
            name = "user_rol",
            joinColumns = @JoinColumn(name = "partner_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id")
    )
    private Set<Rol> roles = new HashSet<>();


    public Partner() {
    }


    public Partner(Integer id, String name, String lastname, String phone, String email,byte passes,String password, String company, Address address,  String user) {
        super(id, name, lastname, phone, email,passes);
        this.password = password;
        this.company = company;
        this.address = address;
        this.user = user;
    }

    public Partner(Integer idP, String name, String lastname, String phone, String email, byte passes,String password, Set<Rol> roles) {
        super(idP, name, lastname, phone, email, passes);
        this.password = password;
        this.roles = roles;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
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
                ", user=" + user +
                ", event=" + event +
                ", dischargeDate=" + dischargeDate +
                ", updateDate=" + updateDate +

                '}';
    }
}
