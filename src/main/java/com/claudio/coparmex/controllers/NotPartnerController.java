package com.claudio.coparmex.controllers;

import com.claudio.coparmex.exceptions.BadRequestExceptions;
import com.claudio.coparmex.models.entities.Event;
import com.claudio.coparmex.models.entities.NotPartner;
import com.claudio.coparmex.models.entities.Person;
import com.claudio.coparmex.services.contracts.EventDAO;
import com.claudio.coparmex.services.contracts.NotPartnerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/notPartner")
public class NotPartnerController {

    private final NotPartnerDAO notPartnerDAOService;

    private final EventDAO eventDAOService;
    /**
     * @Qualifier("notPartnerDAOImp") es el nombre del servicio a utilizar establecido en la clase PartnerDAOImp
     * para poder tener acceso a todos los metodos establecidos en la misma clase
     * */
    @Autowired

    public NotPartnerController(NotPartnerDAO notPartnerDAOService, EventDAO eventDAOService) {
        this.notPartnerDAOService = notPartnerDAOService;
        this.eventDAOService = eventDAOService;
    }

    @GetMapping()
    List<NotPartner> getAllNotPartner(){
        List<NotPartner> allNotPartner = (List<NotPartner>) notPartnerDAOService.getAllNotPartner();

        if (allNotPartner.isEmpty()){
            throw new BadRequestExceptions("No existen datos que mostrar");
        }
        return allNotPartner;



    }


    @GetMapping("/{id}" )
    Person findIdNotPartner(@PathVariable(required = false) Integer id){
     Optional<NotPartner> findId = notPartnerDAOService.findByIdP(id);

     if (!findId.isPresent()){
         throw new BadRequestExceptions("El id %d no existe"+id);
     }
     return findId.get();

    }

    @PutMapping("/{id}")
    NotPartner updateNotPartner(@PathVariable(required = false) Integer id, @RequestBody NotPartner notPartner){
        Optional<NotPartner> findIdNotPartner = notPartnerDAOService.findByIdP(id);

        if (findIdNotPartner.isEmpty()){
            throw new BadRequestExceptions("El id %d no existe en la base de datos");
        }

        NotPartner updateNotPartner;

        updateNotPartner = findIdNotPartner.get();
        updateNotPartner.setName(notPartner.getName());
        updateNotPartner.setLastname(notPartner.getLastname());
        updateNotPartner.setPhone(notPartner.getPhone());
        updateNotPartner.setEmail(notPartner.getEmail());

        return (NotPartner) notPartnerDAOService.save(updateNotPartner);
    }

    @PutMapping("/{idN}/event/{idE}")
    NotPartner addEventNotPartner(@PathVariable Integer idN,@PathVariable Integer idE){

        Optional<NotPartner> byIdN = notPartnerDAOService.findByIdP(idN);
        if (!byIdN.isPresent()){
            throw new BadRequestExceptions(String.format("El id %d no existe en la base de datos",idN));
        }
        Optional<Event> byIdE = eventDAOService.findById(idE);
        if (byIdE.isEmpty()){
            throw new BadRequestExceptions(String.format("El id %d no existe en la base de datos",idE));
        }

        NotPartner notPartner = byIdN.get();
        Event event = byIdE.get();

        notPartner.setEvent(event);

        return (NotPartner) notPartnerDAOService.save(notPartner);
    }

    @PostMapping
    NotPartner saveNotPartner(@RequestBody NotPartner notPartner){
        return (NotPartner) notPartnerDAOService.save(notPartner);
    }
    @DeleteMapping("/{id_n}")
    void deleteNotPartner(@PathVariable(value = "id_n", required = false) Integer id){

       Optional<NotPartner> findId = notPartnerDAOService.findByIdP(id);

       if (findId.isEmpty()){
           throw new BadRequestExceptions("El Id %d no existe en la base de datos"+id);
       }

       notPartnerDAOService.deleteById(id);


    }
}
