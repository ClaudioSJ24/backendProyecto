package com.claudio.coparmex.models.entities;

import com.claudio.coparmex.models.entities.enumerators.KindPerson;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Events extends Person {

    private int idEvent;
    private KindPerson kindPerson;
    private LocalDateTime registerDate;

    public Events() {
    }

    public Events(int idPerson, String name, String lastname, int phone, String email, int idEvent, KindPerson kindPerson) {
        super(idPerson, name, lastname, phone, email);
        this.idEvent = idEvent;
        this.kindPerson = kindPerson;
    }

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public KindPerson getKindPerson() {
        return kindPerson;
    }

    public void setKindPerson(KindPerson kindPerson) {
        this.kindPerson = kindPerson;
    }

    public LocalDateTime getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDateTime registerDate) {
        this.registerDate = registerDate;
    }
}
