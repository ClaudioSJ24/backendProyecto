package com.claudio.coparmex;

import com.claudio.coparmex.models.entities.*;
import com.claudio.coparmex.services.contracts.EventDAO;
import com.claudio.coparmex.services.contracts.NotPartnerDAO;
import com.claudio.coparmex.services.contracts.PartnerDAO;
import com.claudio.coparmex.services.implementations.EventDAOImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

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
        System.out.println(save.toString());

        Person partner = new Partner(null, "Aza", "Hernandez Mendoza", "345678965","er@cmj","Esteren",new Address("4 norte",24,"la orizabeña",78654,"Tehuacan"));
        Person save = partnerDAOService.save(partner);

        System.out.println(save.toString());
        List<Person> all = (List<Person>) partnerDAOService.findAll();
        all.forEach(System.out::println        );

        Event event = new Event(null, "Charla 1", "Tofilo", "Hotl cantarranas");
        Event save = eventDAOService.save(event);
        Event event1 = new Event(null, "Charla 1", "Tofilo", "Hotl cantarranas");
        Event save1 = eventDAOService.save(event1);

        Event event2 = new Event(null, "Charla 1", "Tofilo", "Hotl cantarranas");
        Event save2 = eventDAOService.save(event2);
        Event event3 = new Event(null, "Charla 1", "Tofilo", "Hotl cantarranas");
        Event save3 = eventDAOService.save(event3);

        List<Event> allE = (List<Event>) eventDAOService.findAll();

        allE.forEach(System.out::println);


        Optional<Person> claudio = notPartnerDAOService.findByName("claudo");

        if (claudio.isPresent()){

            System.out.println(claudio.toString());
        }else{
            System.out.println("EL NOMBRE DE " +claudio+ "no esta dentro de la base de datos");
        }



        Person partner = new Partner(null, "Azakjfdk", "Hernandez Mendoza", "345678965","epr@cmj","Esteren",new Address("4 norte",24,"la orizabeña",78654,"Tehuacan"));
        Person save = partnerDAOService.save(partner);

         */

    }
}

