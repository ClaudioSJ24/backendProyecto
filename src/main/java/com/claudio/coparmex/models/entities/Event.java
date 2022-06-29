package com.claudio.coparmex.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "events")


public class Event implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEvent;

    @Column(name = "name_event",nullable = false, length = 80)
    private String nameEvent;

    @Column(name = "name_expositor", nullable = false, length = 30)
    private String nameExpositor;
    @Column(name = "register_date")
    private LocalDateTime registerDate;

    @Column(nullable = false, length = 50)
    private String place;
    @OneToMany(
            mappedBy = "event",
            fetch = FetchType.LAZY
    )
    /**
     * ignora el objeto de tipo Event de la clase NotPartner
     */
    @JsonIgnoreProperties(value = {"event"})
    private Set<NotPartner> notPartner;

    @OneToMany(
            mappedBy = "event",
            fetch = FetchType.LAZY
    )
    @JsonIgnoreProperties(value = {"event"})
    /**
     * ignora el objeto de tipo Event de la clase Partner
     */
    private Set<Partner> partner;

    public Event() {
    }

    public Event(Integer idEvent, String nameEvent, String nameExpositor, String place) {
        this.idEvent = idEvent;
        this.nameEvent = nameEvent;
        this.nameExpositor = nameExpositor;
        this.place = place;
    }

    public Integer getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(Integer idEvent) {
        this.idEvent = idEvent;
    }


    public LocalDateTime getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDateTime registerDate) {
        this.registerDate = registerDate;
    }

    public Set<NotPartner> getNotPartner() {
        return notPartner;
    }

    public void setNotPartner(Set<NotPartner> notPartner) {
        this.notPartner = notPartner;
    }

    public Set<Partner> getPartner() {
        return partner;
    }

    public void setPartner(Set<Partner> partner) {
        this.partner = partner;
    }

    public String getNameEvent() {
        return nameEvent;
    }

    public void setNameEvent(String nameEvent) {
        this.nameEvent = nameEvent;
    }

    public String getNameExpositor() {
        return nameExpositor;
    }

    public void setNameExpositor(String nameExpositor) {
        this.nameExpositor = nameExpositor;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }



    @Override
    public String toString() {
        return "Event{" +
                "idEvent=" + idEvent +
                ", nameEvent='" + nameEvent + '\'' +
                ", nameExpositor='" + nameExpositor + '\'' +
                ", registerDate=" + registerDate +
                ", place='" + place + '\'' +
                '}';
    }

    @PrePersist
    public void beforePersistence(){
        this.registerDate= LocalDateTime.now();

    }

}
