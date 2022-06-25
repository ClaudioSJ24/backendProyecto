package com.claudio.coparmex;

import com.claudio.coparmex.models.entities.*;
import com.claudio.coparmex.services.contracts.EventDAO;
import com.claudio.coparmex.services.contracts.NotPartnerDAO;
import com.claudio.coparmex.services.contracts.PartnerDAO;
import com.claudio.coparmex.services.implementations.EventDAOImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Clase que permite ejecutar la instacia de la clase Event
 * */
@Component
public class EvCommand implements CommandLineRunner {


    @Autowired
    private EventDAO eventDAOService;
    @Autowired
    private NotPartnerDAO notPartnerDAOService;

    @Autowired
    private PartnerDAO partnerDAOService;

    @Override
    public void run(String... args) throws Exception {

        /*
        Event even = new Event(null, "Situation Fiscal","Armando Sanchez","Hotel Casablanca");
        Event save = eventDAOService.save(even);
        System.out.println(save.toString());

        Person notPartner = new NotPartner(null, "claudio", "Sanchez Juarez", "9876543215","clajd@gmail");
        Person save = notPartnerDAOService.save(notPartner);
        System.out.println(save.toString());*/

        Person partner = new Partner(null, "Aldo", "Sanchez Lopez", "9876543212","kdkd@cmj","Esteren",new Address("4 norte",24,"la orizabeña",78654,"Tehuacan"));

        Person save = partnerDAOService.save(partner);

        System.out.println(save.toString());

    }
}
