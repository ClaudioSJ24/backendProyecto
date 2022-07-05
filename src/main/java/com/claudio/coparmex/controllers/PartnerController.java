package com.claudio.coparmex.controllers;

import com.claudio.coparmex.exceptions.BadRequestExceptions;
import com.claudio.coparmex.models.entities.Event;
import com.claudio.coparmex.models.entities.Partner;
import com.claudio.coparmex.models.entities.Person;
import com.claudio.coparmex.services.contracts.EventDAO;
import com.claudio.coparmex.services.contracts.PartnerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/partners")
public class PartnerController {

    private final PartnerDAO partnerDAOService;
    private final EventDAO eventDAOService;


    /**
     * @Qualifier("partnerDAOImp") es el nombre del servicio a utilizar establecido en la clase PartnerDAOImp
     * para poder tener acceso a todos los metodos establecidos en la misma clase
     * */
    @Autowired
    public PartnerController(@Qualifier("partnerDAOImp") PartnerDAO partnerDAOService, EventDAO eventDAOService) {
        this.partnerDAOService = partnerDAOService;
        this.eventDAOService = eventDAOService;
    }

    @GetMapping
    public List<Partner> getAll(){
        List<Partner> partnerAll = (List<Partner>) partnerDAOService.getAllPartner();
        if (partnerAll.isEmpty()){
            throw new BadRequestExceptions("No existen socios");
        }


        return  partnerAll;
    }

    @GetMapping("/event/{idE}")
    List<Partner> getPartnerByEvent(@PathVariable(required = false) Integer idE){
        List<Partner> findPartnerEvent = (List<Partner>) partnerDAOService.findByEventId(idE);
        if (findPartnerEvent.isEmpty()){
            throw new BadRequestExceptions(String.format("El id %d no existe en la base de datos",idE));
        }

        return findPartnerEvent;
    }


    @PostMapping
    public Partner savePartner(@RequestBody Partner partner){

        return (Partner) partnerDAOService.save(partner);

    }

    @GetMapping("/{idP}")
    Person findByIdPartner(@PathVariable(value = "idP", required = false) Integer id){

        Optional<Partner> byId = partnerDAOService.findByIdP(id);

        if (byId.isEmpty()){
            throw new BadRequestExceptions("El id %d no existe"+id);
        }

        return byId.get();
    }



    @PutMapping("/{id_p}")
    Partner updatePartner(@PathVariable(value = "id_p") Integer id,@RequestBody Partner partner){


        Partner partnerUpdate;
        Optional<Partner> findPartner = partnerDAOService.findByIdP(id);

        if(findPartner.isEmpty()){
            throw new BadRequestExceptions("El id %d no existe"+id);
        }

        partnerUpdate=findPartner.get();
        partnerUpdate.setName(partner.getName());
        partnerUpdate.setLastname(partner.getLastname());
        partnerUpdate.setPhone(partner.getPhone());
        partnerUpdate.setEmail(partner.getEmail());
        partnerUpdate.setCompany(partner.getCompany());
        partnerUpdate.setAddress(partner.getAddress());

        
        return (Partner) partnerDAOService.save(partnerUpdate);
    }
    @PutMapping("/{idP}/event/{idE}")
    Partner addEventPartner(@PathVariable Integer idP , @PathVariable Integer idE){

        Optional<Partner> byIdP = partnerDAOService.findByIdP(idP);
        if (byIdP.isEmpty()){
            throw new BadRequestExceptions(String.format("El id %d no existe en la base de datos",idP));
        }

        Optional<Event> byIdE = eventDAOService.findById(idE);

        if (!byIdE.isPresent()){
            throw new BadRequestExceptions(String.format("El id %d no existe en la base de datos",idE));

        }



        Partner partner = byIdP.get();
        Event event = byIdE.get();

        partner.setEvent(event);

        return (Partner) partnerDAOService.save(partner);
    }

    @DeleteMapping("/{id}")
    void deletePartner(@PathVariable Integer id){
        Optional<Partner> idD= partnerDAOService.findByIdP(id);
        if (idD.isEmpty()){
            throw new BadRequestExceptions("El id %d no existe"+id);
        }
        partnerDAOService.deleteById(id);
    }




}
