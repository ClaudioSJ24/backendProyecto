package com.claudio.coparmex.controllers;


import com.claudio.coparmex.exceptions.BadRequestExceptions;
import com.claudio.coparmex.models.entities.Event;
import com.claudio.coparmex.services.contracts.EventDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/events")
public class EventController {


    private final EventDAO eventDAOService;

    @Autowired
    public EventController(EventDAO eventDAOService) {
        this.eventDAOService = eventDAOService;
    }

    @GetMapping
    public List<Event> getAllEvents(){

        List<Event> all = (List<Event>) eventDAOService.findAll();
        if (all.isEmpty()){

            throw  new BadRequestExceptions("Error BadRequestException");
        }
        return all;


    }


    @GetMapping("/{code}")
    public Event findById(@PathVariable(value = "code", required = false) Integer id){

        Optional<Event> finfId = eventDAOService.findById(id);

        if (finfId.isEmpty()){

            throw new BadRequestExceptions("El id %d no existe "+id);

        }

        return finfId.get();

    }

    @PostMapping
    public Event saveEvent(@RequestBody Event event){

       return eventDAOService.save(event);

    }

    @PutMapping("/{id_e}")
    public Event updateEvent(@PathVariable(value = "id_e") Integer id,@RequestBody Event event){

        Event eventUpdate;
        Optional<Event> findEvent = eventDAOService.findById(id);
        if (findEvent.isEmpty()){
            throw new BadRequestExceptions("El id %d no existe en la base de datos"+id);
        }

        eventUpdate = findEvent.get();
        eventUpdate.setNameEvent(event.getNameEvent());
        eventUpdate.setNameExpositor(event.getNameExpositor());
        eventUpdate.setPlace(event.getPlace());

        return eventDAOService.save(eventUpdate);

    }

    @DeleteMapping("/{id}")
    public  void deleteEvent(@PathVariable Integer id){

        eventDAOService.deleteById(id);
    }
}
