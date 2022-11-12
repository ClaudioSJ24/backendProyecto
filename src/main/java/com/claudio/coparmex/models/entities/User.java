package com.claudio.coparmex.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@PrimaryKeyJoinColumn(name = "idPerson")
public class User extends Person{

    @Column(nullable = false, unique = true)
    private String user;
    @Column(nullable = false, unique = true)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_rol",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id")
    )
    private Set<Rol> roles = new HashSet<>();
    @Column(name = "discharge_Date")
    private LocalDateTime dischargeDate;
    @Column(name = "updateDate")
    private LocalDateTime updateDate;

      public User() {
    }

    public User(Integer id, String name, String lastname, String phone, String email, String user, String password) {
        super(id, name, lastname, phone, email);
        this.user = user;
        this.password= password;


    }


    @PrePersist
    public void beforePersistence(){
        this.dischargeDate = LocalDateTime.now();
    }

    @PreUpdate
    public void afterPersistence() {this.updateDate = LocalDateTime.now();}

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

    @Override
    public String toString() {
        return super.toString()+
                "User{" +
                "user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                ", dischargeDate=" + dischargeDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
