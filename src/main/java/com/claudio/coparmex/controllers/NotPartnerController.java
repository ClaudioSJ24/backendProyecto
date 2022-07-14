package com.claudio.coparmex.controllers;

import com.claudio.coparmex.exceptions.BadRequestExceptions;
import com.claudio.coparmex.models.entities.Event;
import com.claudio.coparmex.models.entities.NotPartner;
import com.claudio.coparmex.models.entities.Person;
import com.claudio.coparmex.services.contracts.EventDAO;
import com.claudio.coparmex.services.contracts.NotPartnerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/notPartners")
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

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @GetMapping("/getAll")
    ResponseEntity<?> getAllNotPartner(){

        Map<String,Object> message = new HashMap<>();
        List<NotPartner> allNotPartner = (List<NotPartner>) notPartnerDAOService.getAllNotPartner();


        if (allNotPartner.isEmpty()){
            //throw new BadRequestExceptions("No existen datos que mostrar");
            message.put("Success",Boolean.FALSE);
            message.put("Message", String.format("No existen datos que mostrar"));
            return ResponseEntity.badRequest().body(message);
        }

        message.put("Success", Boolean.TRUE);
        message.put("Data", allNotPartner);
        return ResponseEntity.ok(message);



    }


    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @GetMapping("/getId/{id}" )
    ResponseEntity<?> findIdNotPartner(@PathVariable(required = false) Integer id){

        Map<String,Object> message = new HashMap<>();
        Optional<NotPartner> findId = notPartnerDAOService.findByIdP(id);

     if (!findId.isPresent()){
         //throw new BadRequestExceptions("El id %d no existe"+id);
         message.put("Success", Boolean.FALSE);
         message.put("Message", String.format("El id %d no existe en la base de datos",id));
         return ResponseEntity.badRequest().body(message);
     }
     message.put("Success", Boolean.TRUE);
     message.put("Data", findId);
     return ResponseEntity.ok(message);

    }
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @GetMapping("event/{idE}")
    ResponseEntity<?> findNotPartnerEvent(@PathVariable Integer idE){

        Map<String,Object> message = new HashMap<>();

        List<NotPartner> listEvent = (List<NotPartner>) notPartnerDAOService.findByIdEvent(idE);
        if (listEvent.isEmpty()){
            //throw new BadRequestExceptions("No existen personas en la base de datos con el evento "+idE);
            message.put("Success", Boolean.FALSE);
            message.put("Message", String.format("El id %d no existe en la base de datos",idE));
            return ResponseEntity.badRequest().body(message);
        }

        message.put("Success", Boolean.TRUE);
        message.put("Message", listEvent);
        return  ResponseEntity.ok(message);

    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @PutMapping("/update/{id}")
    ResponseEntity updateNotPartner(@PathVariable(required = false) Integer id, @RequestBody NotPartner notPartner){

        Map<String,Object> message = new HashMap<>();
        Optional<NotPartner> findIdNotPartner = notPartnerDAOService.findByIdP(id);

        if (findIdNotPartner.isEmpty()){
            //throw new BadRequestExceptions("El id %d no existe en la base de datos");
            message.put("Success", Boolean.FALSE);
            message.put("Message", String.format("El id %d no existe en la base de datos",id));
            return ResponseEntity.badRequest().body(message);
        }

        NotPartner updateNotPartner;

        updateNotPartner = findIdNotPartner.get();
        updateNotPartner.setName(notPartner.getName());
        updateNotPartner.setLastname(notPartner.getLastname());
        updateNotPartner.setPhone(notPartner.getPhone());
        updateNotPartner.setEmail(notPartner.getEmail());

        message.put("Success", Boolean.TRUE);
        message.put("Message", notPartnerDAOService.save(updateNotPartner));
        return ResponseEntity.ok(message);


    }

    @PutMapping("/{idN}/event/{idE}")
    ResponseEntity<?> addEventNotPartner(@PathVariable Integer idN,@PathVariable Integer idE){

        Map<String,Object> message = new HashMap<>();
        Optional<NotPartner> byIdN = notPartnerDAOService.findByIdP(idN);
        if (!byIdN.isPresent()){
            //throw new BadRequestExceptions(String.format("El id %d no existe en la base de datos",idN));
            message.put("Success", Boolean.FALSE);
            message.put("Message", String.format("El id %d no existe en la base de datos",idN));
            return ResponseEntity.badRequest().body(message);

        }
        Optional<Event> byIdE = eventDAOService.findById(idE);
        if (byIdE.isEmpty()) {
            //throw new BadRequestExceptions(String.format("El id %d no existe en la base de datos",idE));
            message.put("Success", Boolean.FALSE);
            message.put("Message", String.format("El id %d no existe en la base de datos",idE));
            return ResponseEntity.badRequest().body(message);
        }

        NotPartner notPartner = byIdN.get();
        Event event = byIdE.get();

        notPartner.setEvent(event);

        message.put("Success", Boolean.TRUE);
        message.put("Message", notPartnerDAOService.save(notPartner));
        return ResponseEntity.ok(message);


    }

    @PostMapping("/save")
    ResponseEntity<?> saveNotPartner(@RequestBody NotPartner notPartner){
        Map<String, Object> message = new HashMap<>();
        message.put("Success", Boolean.TRUE);
        message.put("Message", notPartnerDAOService.save(notPartner));
        return ResponseEntity.ok(message);


    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @DeleteMapping("/delete/{id_n}")
    void deleteNotPartner(@PathVariable(value = "id_n", required = false) Integer id){

       Optional<NotPartner> findId = notPartnerDAOService.findByIdP(id);

       if (findId.isEmpty()){
           throw new BadRequestExceptions("El Id %d no existe en la base de datos"+id);
       }

       notPartnerDAOService.deleteById(id);


    }
}
