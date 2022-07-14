package com.claudio.coparmex.controllers;

import com.claudio.coparmex.exceptions.BadRequestExceptions;
import com.claudio.coparmex.models.entities.*;
import com.claudio.coparmex.models.entities.enumerators.RolName;
import com.claudio.coparmex.security.dto.NewPersonDto;
import com.claudio.coparmex.security.jwt.JwtProvider;
import com.claudio.coparmex.services.contracts.EventDAO;
import com.claudio.coparmex.services.contracts.PartnerDAO;
import com.claudio.coparmex.services.implementations.PartnerDAOImp;
import com.claudio.coparmex.services.implementations.RolDAOImp;
import com.claudio.coparmex.services.implementations.UserDetailsServiceImp;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/partners")
@CrossOrigin
public class PartnerController {

    private final PartnerDAO partnerDAOService;
    private final EventDAO eventDAOService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RolDAOImp rolDAOImp;
    /**
     * @Qualifier("partnerDAOImp") es el nombre del servicio a utilizar establecido en la clase PartnerDAOImp
     * para poder tener acceso a todos los metodos establecidos en la misma clase
     * */
    @Autowired
    public PartnerController(@Qualifier("partnerDAOImp") PartnerDAO partnerDAOService, EventDAO eventDAOService) {
        this.partnerDAOService = partnerDAOService;
        this.eventDAOService = eventDAOService;
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
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

    @PreAuthorize("hasRole('ADMINISTRATOR')")
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
    public ResponseEntity<?> save(@RequestBody NewPersonDto partner){
        /**
         * {
         *     "class": "partner",
         *
         *         "name": "Claudio",
         *         "lastname": "anastacia Mendoza",
         *         "phone": "345678965",
         *         "email": "padrino@ljdfkh",
         *         "passes": 4,
         *         "password":"12345",
         *         "company": "Sony",
         *         "address": {
         *             "street": "4 norte",
         *             "number": 24,
         *             "colony": "la orizabeña",
         *             "codePostal": 78654,
         *             "city": "Puebla"
         *         },
         *         "user": "que pasa hay",
         *         "roles": ["administrator"] -->Campo opcional
         *
         * }
         *
         */

        Optional<Partner> byUser = partnerDAOService.findByUser(partner.getUser());

        Optional<Partner> byEmail = partnerDAOService.findByEmail(partner.getEmail());

        Map<String,Object> message = new HashMap<>();

        if (byUser.isPresent()){

            //return new  ResponseEntity(new BadRequestExceptions("El nombre ya existe"), HttpStatus.BAD_REQUEST);
            message.put("Success", Boolean.FALSE);
            message.put("Message", String.format("El nombre de usuarios %s ya existe ",partner.getUser()));

            return ResponseEntity.badRequest().body(message);
        }

        if (byEmail.isPresent()){

           // return new  ResponseEntity(new BadRequestExceptions("El correo ya existe"), HttpStatus.BAD_REQUEST);
            message.put("Success", Boolean.FALSE);
            message.put("Message", String.format("El email %s ya existe ",partner.getEmail()));

            return ResponseEntity.badRequest().body(message);
        }

        Partner savePartner = new Partner(null,partner.getName(), partner.getLastname(), partner.getPhone(), partner.getEmail(), partner.getPasses(),
                                 passwordEncoder.encode(partner.getPassword()), partner.getCompany(), partner.getAddress(), partner.getUser());
        Set<Rol> roles = new HashSet<>();

        roles.add(rolDAOImp.findByNameRol(RolName.ROL_PARTNER).get());

        if (partner.getRoles().contains("administrator")){
            roles.add(rolDAOImp.findByNameRol(RolName.ROL_ADMINISTRATOR).get());
        }

        savePartner.setRoles(roles);
        message.put("Success", Boolean.TRUE);
        message.put("Data", partnerDAOService.save(savePartner));
        return ResponseEntity.ok(message);

        /*partnerDAOService.save(savePartner);
        return new  ResponseEntity(new BadRequestExceptions("Usuario registrado"), HttpStatus.CREATED);*/
    }


    /*@PostMapping("/save")
    ResponseEntity<?> savePartner(@RequestBody Partner partner){

        Map<String,Object> message = new HashMap<>();

        message.put("Success", Boolean.TRUE);
        message.put("Data", partnerDAOService.save(partner));
        return ResponseEntity.ok(message);


    }*/

    @PreAuthorize("hasRole('ADMINISTRATOR')")
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



    @PreAuthorize("hasRole('ADMINISTRATOR')")
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

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @DeleteMapping("/delete/{id}")
    void deletePartner(@PathVariable Integer id){
        Optional<Partner> idD= partnerDAOService.findByIdP(id);
        if (idD.isEmpty()){
            throw new BadRequestExceptions("El id %d no existe"+id);
        }
        partnerDAOService.deleteById(id);
    }




}
