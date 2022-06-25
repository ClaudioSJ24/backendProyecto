package com.claudio.coparmex.models.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "not_partners")
@PrimaryKeyJoinColumn(name = "idPerson")
public class NotPartner extends Person{

    @Column(name = "discharge_Date")
    private LocalDateTime dischargeDate;

    @ManyToOne(
            optional = true,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "idEvent")
    private Event event;


    public NotPartner() {
    }

    public NotPartner(Integer id, String name, String lastname, String phone, String email) {
        super(id, name, lastname, phone, email);

    }

    public Event getEventos() {
        return event;
    }

    public void setEventos(Event eventos) {
        this.event = eventos;
    }

    @PrePersist
    public void beforePersistence(){
        this.dischargeDate = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return super.toString() +
                "NotPartner{}";
    }
}
