package com.claudio.coparmex.models.entities;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "people")
/***
 * Establece herencia a la clase Partner y NotPartner
 */
@Inheritance(strategy = InheritanceType.JOINED)
/**
 * Al ser una clase abstracta es necesario  establecer el nombre(JsonTypeInfo.Id.NAME) de las clases que heredan de la
 * clase persona mediante la propiedad property = "class"
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "class"
)
/**
 * Se establecen las clases a referenciar, por ejemplo
 * {
 *     "class": "partner",
 *     "name": "pedro",
 *     "lastname": "hernandes",
 *     "phone": "234543",
 *     "email": "ijdh@jfgg",
 *     "company": "Transportes",
 *     "address":{
 *         "street": "5 poniente",
 *         "number": 8,
 *         "colony": "Reforma",
 *         "codePostal": 98654,
 *         "city": "Mexico"
 *     }
 *
 * }
 */
@JsonSubTypes({
        @JsonSubTypes.Type(value = Partner.class, name = "partner"),
        @JsonSubTypes.Type(value = NotPartner.class, name = "notPartner")
})
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

    @Column(nullable = false)
    private int pasess;

    public Person() {
    }

    public Person(Integer idP, String name, String lastname, String phone, String email, int passes) {
        this.idP = idP;
        this.name = name;
        this.lastname = lastname;
        this.phone = phone;
        this.email = email;
        this.pasess = passes;
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

    public int getPasess() {
        return pasess;
    }

    public void setPasess(int pasess) {
        this.pasess = pasess;
    }

    @Override
    public String toString() {
        return "Person{" +
                "idPerson=" + idP +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", phone=" + phone +
                ", email='" + email + '\'' +
                ", pasess=" + pasess +
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
