package com.claudio.coparmex.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "not_partners")
@PrimaryKeyJoinColumn(name = "idPerson")
public class NotPartner extends Person{

    @Column(name = "discharge_Date")
    private LocalDateTime dischargeDate;
    @Column(name = "updateDate")
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
            "hibernateLazyInitializer","notPartner"})
    private Event event;

    public NotPartner() {
    }

    public NotPartner(Integer id, String name, String lastname, String phone, String email, byte passes) {
        super(id, name, lastname, phone, email, passes);

    }


    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }


    @PrePersist
    public void beforePersistence(){
        this.dischargeDate = LocalDateTime.now();
    }

    @PreUpdate
    public void afterPersistence() {this.updateDate = LocalDateTime.now();}

    @Override
    public String toString() {
        return super.toString()+
                "NotPartner{" +
                "dischargeDate=" + dischargeDate +
                "updateDate=" + updateDate +
                ", event=" + event +
                '}';
    }
}
