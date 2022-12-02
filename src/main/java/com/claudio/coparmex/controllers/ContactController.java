package com.claudio.coparmex.controllers;

import com.claudio.coparmex.models.entities.Contact;
import com.claudio.coparmex.models.entities.Partner;
import com.claudio.coparmex.services.contracts.ContactDAO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/contacts")
@CrossOrigin

public class ContactController {

    private final ContactDAO contactDAOService;

    public ContactController(@Qualifier("contactDAOImp") ContactDAO contactDAOService) {
        this.contactDAOService = contactDAOService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){

        Map<String, Object> message = new HashMap<>();
        List<Contact> contactAll = (List<Contact>) contactDAOService.getAllContact();

        if (contactAll.isEmpty()){
            message.put("Success",Boolean.FALSE);
            message.put("Message", String.format("No existen datos que mostrar"));
            return ResponseEntity.badRequest().body(message);
        }

        message.put("Success",Boolean.TRUE);
        message.put("responseContact", contactAll);
        return ResponseEntity.ok(message);

    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Contact contact){

        Map<String, Object> message = new HashMap<>();
        Contact saveContact = new Contact(null, contact.getName(), contact.getLastname(), contact.getPhone(), contact.getEmail(), contact.getCompany(), contact.getActivity(), contact.getMessage());
        message.put("Success", Boolean.TRUE);
        message.put("responseContact", contactDAOService.save(saveContact));
        return ResponseEntity.ok(message);
    }

    @GetMapping("/getId/{idP}")
    public ResponseEntity<?> findByIdContact(@PathVariable(value = "idP", required = false)Integer id){
        Map<String,Object> message = new HashMap<>();
        Optional<Contact> byId = contactDAOService.findByIdP(id);
        if(!byId.isPresent()){
            message.put("Success", Boolean.FALSE);
            message.put("Message", String.format("No existe un id %d asignado a un socio  en la base de datos",id));
            return ResponseEntity.badRequest().body(message);
        }
        message.put("Success", Boolean.TRUE);
        message.put("responseContact", byId);
        return ResponseEntity.ok(message);
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<?> deletePartner(@PathVariable Integer id) {

        Map<String, Object> message = new HashMap<>();
        Optional<Contact> findId = contactDAOService.findByIdP(id);
        if (findId.isEmpty()) {
            message.put("Success", Boolean.FALSE);
            message.put("Message", String.format("No existe un id %d asignado a un socio  en la base de datos", id));

            return ResponseEntity.badRequest().body(message);
        }

        message.put("Success", Boolean.TRUE);
        message.put("responseContact", contactDAOService.deleteById(id));

        return ResponseEntity.ok(message);

    }

}
