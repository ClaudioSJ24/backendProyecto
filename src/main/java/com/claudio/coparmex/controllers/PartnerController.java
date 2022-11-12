package com.claudio.coparmex.controllers;

import com.claudio.coparmex.exceptions.BadRequestExceptions;
import com.claudio.coparmex.models.entities.*;
import com.claudio.coparmex.security.dto.NewPersonDto;
import com.claudio.coparmex.services.contracts.PartnerDAO;
import com.claudio.coparmex.services.implementations.RolDAOImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
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



    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Partner partner){
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

         Map<String,Object> message = new HashMap<>();

        Optional<Partner> byEmail = partnerDAOService.findByEmail(partner.getEmail());

        if (byEmail.isPresent()) {

            // return new  ResponseEntity(new BadRequestExceptions("El correo ya existe"), HttpStatus.BAD_REQUEST);
            message.put("Success", Boolean.FALSE);
            message.put("Message", String.format("El email %s ya existe ",partner.getEmail()));

            return ResponseEntity.badRequest().body(message);



        }

        Partner savePartner = new Partner(null, partner.getName(), partner.getLastname(), partner.getPhone(), partner.getEmail(), partner.getCompany(), partner.getAddress());

       /* Partner savePartner = new Partner(null,partner.getName(), partner.getLastname(), partner.getPhone(), partner.getEmail(),
                                 passwordEncoder.encode(partner.getPassword()), partner.getCompany(), partner.getAddress(), partner.getUser());
        Set<Rol> roles = new HashSet<>();

        roles.add(rolDAOImp.findByNameRol(RolName.ROL_PARTNER).get());

        if (partner.getRoles().contains("administrator")){
            roles.add(rolDAOImp.findByNameRol(RolName.ROL_ADMINISTRATOR).get());
        }

        savePartner.setRoles(roles);*/
        message.put("Success", Boolean.TRUE);
        message.put("Data", partnerDAOService.save(savePartner));
        return ResponseEntity.ok(message);

        /*partnerDAOService.save(savePartner);
        return new  ResponseEntity(new BadRequestExceptions("Usuario registrado"), HttpStatus.CREATED);*/
    }




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
