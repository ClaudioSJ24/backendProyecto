package com.claudio.coparmex.models.entities;

import com.claudio.coparmex.models.entities.enumerators.RolName;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "roles")
public class Rol implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idR;
    @Column(name = "name_rol",nullable = false)
    @Enumerated(EnumType.STRING)
    private RolName nameRol;

    public Rol() {
    }

    public Rol(Integer idR, RolName nameRol) {
        this.idR = idR;
        this.nameRol = nameRol;
    }

    public Integer getIdR() {
        return idR;
    }

    public void setIdR(Integer idR) {
        this.idR = idR;
    }

    public RolName getNameRol() {
        return nameRol;
    }

    public void setNameRol(RolName nameRol) {
        this.nameRol = nameRol;
    }
}
