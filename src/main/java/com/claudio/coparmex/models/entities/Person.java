package com.claudio.coparmex.models.entities;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "people")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idP;
    @Column(nullable = false, length = 30)
    private String name;
    @Column(nullable = false, length = 50)
    private String lastname;
    @Column(nullable = false,length = 10)
    private String phone;
    @Column(nullable = false, unique = true)
    private String email;

    public Person() {
    }

    public Person(Integer idP, String name, String lastname, String phone, String email) {
        this.idP = idP;
        this.name = name;
        this.lastname = lastname;
        this.phone = phone;
        this.email = email;
    }

    public Integer getIdP() {
        return idP;
    }

    public void setIdP(Integer idP) {
        this.idP = idP;
    }

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

    @Override
    public String toString() {
        return "Person{" +
                "idPerson=" + idP +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", phone=" + phone +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return idP == person.idP;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idP);
    }
}