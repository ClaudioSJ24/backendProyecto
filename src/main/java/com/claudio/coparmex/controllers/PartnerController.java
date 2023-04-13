package com.claudio.coparmex.controllers;

import com.claudio.coparmex.models.entities.*;
import com.claudio.coparmex.services.contracts.PartnerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/partners")
@CrossOrigin
public class PartnerController {

    private final PartnerDAO partnerDAOService;

    /**
     * @Qualifier("partnerDAOImp") es el nombre del servicio a utilizar establecido en la clase PartnerDAOImp
     * para poder tener acceso a todos los metodos establecidos en la misma clase
     * */
    @Autowired
    public PartnerController(@Qualifier("partnerDAOImp") PartnerDAO partnerDAOService) {
        this.partnerDAOService = partnerDAOService;

    }

   /* @PreAuthorize("hasRole('ADMINISTRATOR')")*/
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
        message.put("responsePartner", partnerAll);
        return ResponseEntity.ok(message);
    }



    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Partner partner){

         Map<String,Object> message = new HashMap<>();

        Optional<Partner> byEmail = partnerDAOService.findByEmail(partner.getEmail());
        if (byEmail.isPresent()) {
            message.put("Success", Boolean.FALSE);
            message.put("Message", String.format("El email %s ya existe ",partner.getEmail()));
            return ResponseEntity.badRequest().body(message);
        }
        Partner savePartner = new Partner(null, partner.getName(), partner.getLastname(),
                partner.getPhone(), partner.getEmail(), partner.getCompany(), partner.getAddress());
        message.put("Success", Boolean.TRUE);
        message.put("responsePartner", partnerDAOService.save(savePartner));
        return ResponseEntity.ok(message);

    }




   /* @PreAuthorize("hasRole('ADMINISTRATOR')")*/
    @GetMapping("/getId/{idP}")
    ResponseEntity<?> findByIdPartner(@PathVariable(value = "idP", required = false) Integer id){

        Map<String,Object> message = new HashMap<>();
        Optional<Partner> byId = partnerDAOService.findByIdP(id);

        if (!byId.isPresent()){
            //throw new BadRequestExceptions("El id %d no existe"+id);
            message.put("Success", Boolean.FALSE);
            message.put("Message", String.format("No existe un id %d asignado a un socio  en la base de datos",id));
            return ResponseEntity.badRequest().body(message);
        }
        message.put("Success", Boolean.TRUE);
        message.put("responsePartner", byId);
        return ResponseEntity.ok(message);
    }



   /* @PreAuthorize("hasRole('ADMINISTRATOR')")*/
    @PutMapping("/update/{id_p}")
    ResponseEntity<?> updatePartner(@PathVariable(value = "id_p") Integer id,@RequestBody Partner partner){

        Map<String,Object> message = new HashMap<>();

        Partner partnerUpdate;
        Optional<Partner> findPartner = partnerDAOService.findByIdP(id);

        if (findPartner.isEmpty()){
            //throw new BadRequestExceptions("El id %d no existe en la base de datos");
            message.put("Success", Boolean.FALSE);
            message.put("Message", String.format("No existe un id %d asignado a un socio  en la base de datos",id));
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
        message.put("responsePartner", partnerDAOService.save(partnerUpdate));
        return ResponseEntity.ok(message);
    }

    /*@PreAuthorize("hasRole('ADMINISTRATOR')")*/
    @DeleteMapping("/delete/{id}")
    ResponseEntity<?> deletePartner(@PathVariable Integer id) {

        Map<String, Object> message = new HashMap<>();
        Optional<Partner> findId = partnerDAOService.findByIdP(id);
        if (findId.isEmpty()) {
            message.put("Success", Boolean.FALSE);
            message.put("Message", String.format("No existe un id %d asignado a un socio  en la base de datos", id));

            return ResponseEntity.badRequest().body(message);
        }

        message.put("Success", Boolean.TRUE);
        message.put("responseUser", partnerDAOService.deleteById(id));

        return ResponseEntity.ok(message);

    }


}
