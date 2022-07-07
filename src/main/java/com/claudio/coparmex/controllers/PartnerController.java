package com.claudio.coparmex.controllers;

import com.claudio.coparmex.exceptions.BadRequestExceptions;
import com.claudio.coparmex.models.entities.Event;
import com.claudio.coparmex.models.entities.Partner;
import com.claudio.coparmex.models.entities.Person;
import com.claudio.coparmex.services.contracts.EventDAO;
import com.claudio.coparmex.services.contracts.PartnerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    @GetMapping("/getAll")
    ResponseEntity<?> getAll(){

        Map<String,Object> message = new HashMap<>();
        List<Partner> partnerAll = (List<Partner>) partnerDAOService.getAllPartner();
        if (partnerAll.isEmpty()){
            //throw new BadRequestExceptions("No existen socios");
            message.put("Success",Boolean.FALSE);
            message.put("Message", String.format("No existen datos que mostrar"));
            return ResponseEntity.badRequest().body(message);
        }


        message.put("Success",Boolean.TRUE);
        message.put("Data", partnerAll);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/event/{idE}")
    ResponseEntity<?> getPartnerByEvent(@PathVariable(required = false) Integer idE){

        Map<String,Object> message = new HashMap<>();
        List<Partner> findPartnerEvent = (List<Partner>) partnerDAOService.findByEventId(idE);
        if (findPartnerEvent.isEmpty()){
            //throw new BadRequestExceptions(String.format("El id %d no existe en la base de datos",idE));
            message.put("Success", Boolean.FALSE);
            message.put("Message", String.format("El id %d no existe en la base de datos",idE));
            return ResponseEntity.badRequest().body(message);
        }

        message.put("Success", Boolean.TRUE);
        message.put("Message",findPartnerEvent);
        return ResponseEntity.ok(message);


    }


    @PostMapping("/save")
    ResponseEntity<?> savePartner(@RequestBody Partner partner){

        Map<String,Object> message = new HashMap<>();

        message.put("Success", Boolean.TRUE);
        message.put("Data", partnerDAOService.save(partner));
        return ResponseEntity.ok(message);


    }

    @GetMapping("/getId/{idP}")
    ResponseEntity<?> findByIdPartner(@PathVariable(value = "idP", required = false) Integer id){

        Map<String,Object> message = new HashMap<>();
        Optional<Partner> byId = partnerDAOService.findByIdP(id);

        if (!byId.isPresent()){
            //throw new BadRequestExceptions("El id %d no existe"+id);
            message.put("Success", Boolean.FALSE);
            message.put("Message", String.format("El id %d no existe en la base de datos",id));
            return ResponseEntity.badRequest().body(message);
        }
        message.put("Success", Boolean.TRUE);
        message.put("Data", byId);
        return ResponseEntity.ok(message);
    }



    @PutMapping("/update/{id_p}")
    ResponseEntity<?> updatePartner(@PathVariable(value = "id_p") Integer id,@RequestBody Partner partner){

        Map<String,Object> message = new HashMap<>();

        Partner partnerUpdate;
        Optional<Partner> findPartner = partnerDAOService.findByIdP(id);

        if (findPartner.isEmpty()){
            //throw new BadRequestExceptions("El id %d no existe en la base de datos");
            message.put("Success", Boolean.FALSE);
            message.put("Message", String.format("El id %d no existe en la base de datos",id));
            return ResponseEntity.badRequest().body(message);
        }

        partnerUpdate=findPartner.get();
        partnerUpdate.setName(partner.getName());
        partnerUpdate.setLastname(partner.getLastname());
        partnerUpdate.setPhone(partner.getPhone());
        partnerUpdate.setEmail(partner.getEmail());
        partnerUpdate.setCompany(partner.getCompany());
        partnerUpdate.setAddress(partner.getAddress());


        message.put("Success", Boolean.TRUE);
        message.put("Message", partnerDAOService.save(partnerUpdate));
        return ResponseEntity.ok(message);
    }
    @PutMapping("/{idP}/event/{idE}")
    ResponseEntity<?> addEventPartner(@PathVariable Integer idP , @PathVariable Integer idE){

        Map<String,Object> message = new HashMap<>();
        Optional<Partner> byIdP = partnerDAOService.findByIdP(idP);
        if (!byIdP.isPresent()){
            //throw new BadRequestExceptions(String.format("El id %d no existe en la base de datos",idN));
            message.put("Success", Boolean.FALSE);
            message.put("Message", String.format("El id %d no existe en la base de datos",idP));
            return ResponseEntity.badRequest().body(message);

        }
        Optional<Event> byIdE = eventDAOService.findById(idE);
        if (byIdE.isEmpty()) {
            //throw new BadRequestExceptions(String.format("El id %d no existe en la base de datos",idE));
            message.put("Success", Boolean.FALSE);
            message.put("Message", String.format("El id %d no existe en la base de datos", idE));
            return ResponseEntity.badRequest().body(message);
        }


        Partner partner = byIdP.get();
        Event event = byIdE.get();

        partner.setEvent(event);

        message.put("Success", Boolean.TRUE);
        message.put("Message", partnerDAOService.save(partner));
        return ResponseEntity.ok(message);
    }

    @DeleteMapping("/delete/{id}")
    void deletePartner(@PathVariable Integer id){
        Optional<Partner> idD= partnerDAOService.findByIdP(id);
        if (idD.isEmpty()){
            throw new BadRequestExceptions("El id %d no existe"+id);
        }
        partnerDAOService.deleteById(id);
    }




}
