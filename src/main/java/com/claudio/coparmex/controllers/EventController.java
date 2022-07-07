package com.claudio.coparmex.controllers;


import com.claudio.coparmex.exceptions.BadRequestExceptions;
import com.claudio.coparmex.models.entities.Event;
import com.claudio.coparmex.services.contracts.EventDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/events")
public class EventController {


    private final EventDAO eventDAOService;


    @Autowired
    public EventController(EventDAO eventDAOService) {
        this.eventDAOService = eventDAOService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllEvents(){

        Map<String, Object> messaje = new HashMap<>();

        List<Event> all = (List<Event>) eventDAOService.findAll();
        if (all.isEmpty()){

            //throw  new BadRequestExceptions("Error BadRequestException");
            messaje.put("Success", Boolean.FALSE);
            messaje.put("message", String.format("No existen registros en la base de datos"));
            return ResponseEntity.badRequest().body(messaje);
        }

        messaje.put("Success", Boolean.TRUE);
        messaje.put("Data", all);
        return ResponseEntity.ok(messaje);


    }


    @GetMapping("/getId/{code}")
    public ResponseEntity<?> findById(@PathVariable(value = "code", required = false) Integer id){

        Map<String, Object> messaje = new HashMap<>();
        Optional<Event> finfId = eventDAOService.findById(id);

        if (finfId.isEmpty()){

            //throw new BadRequestExceptions("El id %d no existe "+id);
            messaje.put("Success", Boolean.FALSE);
            messaje.put("Message", String.format("El id %d no existe ",id));
            return ResponseEntity.badRequest().body(messaje);

        }

        messaje.put("Success",Boolean.TRUE);
        messaje.put("Data",finfId);

        return ResponseEntity.ok(messaje);

    }

    @PostMapping("/save")
    public ResponseEntity<?> saveEvent(@RequestBody Event event){

        Map<String, Object> messaje = new HashMap<>();
        messaje.put("Success", Boolean.TRUE);
        messaje.put("Data", eventDAOService.save(event));

       return ResponseEntity.ok(messaje);

    }

    @PutMapping("/update/{id_e}")
    public ResponseEntity<?> updateEvent(@PathVariable(value = "id_e") Integer id,@RequestBody Event event){

        Map<String, Object> messaje = new HashMap<>();
        Event eventUpdate;
        Optional<Event> findEvent = eventDAOService.findById(id);
        if (findEvent.isEmpty()){

           // throw new BadRequestExceptions("El id %d no existe en la base de datos"+id);
            messaje.put("Success", Boolean.FALSE);
            messaje.put("Message",String.format("El id %d no existe en la base de datos",id));
            return ResponseEntity.badRequest().body(messaje);
        }

        eventUpdate = findEvent.get();
        eventUpdate.setNameEvent(event.getNameEvent());
        eventUpdate.setNameExpositor(event.getNameExpositor());
        eventUpdate.setPlace(event.getPlace());

        messaje.put("Success", Boolean.TRUE);
        messaje.put("Data", eventDAOService.save(eventUpdate));
        return ResponseEntity.ok(messaje);

    }

    @DeleteMapping("delete/{id}")
    public  ResponseEntity<?> deleteEvent(@PathVariable Integer id){

        Map<String, Object> messaje = new HashMap<>();
        Optional<Event> byId = eventDAOService.findById(id);
        if(!byId.isPresent()){
            messaje.put("Success", Boolean.FALSE);
            messaje.put("Message", String.format("El id %d no existe en la base de datos",id));
            return ResponseEntity.badRequest().body(messaje);
        }

        messaje.put("Success", Boolean.TRUE);
        messaje.put("Data",eventDAOService.deleteById(id) );
        return ResponseEntity.ok(messaje);
    }
}
