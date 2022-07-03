package com.claudio.coparmex.controllers;

import com.claudio.coparmex.exceptions.BadRequestExceptions;
import com.claudio.coparmex.models.entities.Partner;
import com.claudio.coparmex.models.entities.Person;
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

    /**
     * @Qualifier("partnerDAOImp") es el nombre del servicio a utilizar establecido en la clase PartnerDAOImp
     * para poder tener acceso a todos los metodos establecidos en la misma clase
     * */
    @Autowired
    public PartnerController(@Qualifier("partnerDAOImp") PartnerDAO partnerDAOService) {
        this.partnerDAOService = partnerDAOService;
    }

    @GetMapping
    public List<Partner> getAll(){
        List<Partner> partnerAll = (List<Partner>) partnerDAOService.getAllPartner();
        if (partnerAll.isEmpty()){
            throw new BadRequestExceptions("No existen socios");
        }


        return  partnerAll;
    }


    @PostMapping
    public Partner savePartner(@RequestBody Partner partner){

        return (Partner) partnerDAOService.save(partner);

    }

    @GetMapping("/{idP}")
    Person findByIdPartner(@PathVariable(value = "idP", required = false) Integer id){

        Optional<Person> byId = partnerDAOService.findById(id);

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

    @DeleteMapping("/{id}")
    void deletePartner(@PathVariable Integer id){
        Optional<Partner> idD= partnerDAOService.findByIdP(id);
        if (idD.isEmpty()){
            throw new BadRequestExceptions("El id %d no existe"+id);
        }
        partnerDAOService.deleteById(id);
    }




}
