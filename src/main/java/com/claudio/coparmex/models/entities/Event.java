package com.claudio.coparmex.models.entities;

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
    private Set<NotPartner> notPartner;

    @OneToMany(
            mappedBy = "event",
            fetch = FetchType.LAZY
    )
    private Set<Partner> pather;

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

    public Set<Partner> getPather() {
        return pather;
    }

    public void setPather(Set<Partner> pather) {
        this.pather = pather;
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
