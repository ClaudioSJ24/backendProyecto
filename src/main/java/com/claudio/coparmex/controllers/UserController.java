package com.claudio.coparmex.controllers;

import com.claudio.coparmex.exceptions.BadRequestExceptions;
import com.claudio.coparmex.models.entities.Rol;
import com.claudio.coparmex.models.entities.User;
import com.claudio.coparmex.models.entities.enumerators.RolName;
import com.claudio.coparmex.security.dto.NewPersonDto;
import com.claudio.coparmex.services.contracts.UserDAO;
import com.claudio.coparmex.services.implementations.RolDAOImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.SecondaryTable;
import java.util.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserDAO userDAOService;


    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RolDAOImp rolDAOImp;

    /**
     * @Qualifier("notPartnerDAOImp") es el nombre del servicio a utilizar establecido en la clase PartnerDAOImp
     * para poder tener acceso a todos los metodos establecidos en la misma clase
     * */
    @Autowired

    public UserController(@Qualifier("userDAOImp") UserDAO userDAOService) {
        this.userDAOService = userDAOService;

    }

   /* @PreAuthorize("hasRole('ADMINISTRATOR')")*/
    @GetMapping("/getAll")
    ResponseEntity<?> getAllUser(){

        Map<String,Object> message = new HashMap<>();
        List<User> allUser = (List<User>) userDAOService.getAllUsers();


        if (allUser.isEmpty()){
            //throw new BadRequestExceptions("No existen datos que mostrar");
            message.put("Success",Boolean.FALSE);
            message.put("Message", String.format("No existen datos que mostrar"));
            return ResponseEntity.badRequest().body(message);
        }

        message.put("Success", Boolean.TRUE);
        message.put("responseUser", allUser);
        return ResponseEntity.ok(message);



    }


   /* @PreAuthorize("hasRole('ADMINISTRATOR')")*/
    @GetMapping("/getId/{id}" )
    ResponseEntity<?> findIdUser(@PathVariable(required = false) Integer id){

        Map<String,Object> message = new HashMap<>();
        Optional<User> findId = userDAOService.findByIdP(id);

     if (!findId.isPresent()){
         //throw new BadRequestExceptions("El id %d no existe"+id);
         message.put("Success", Boolean.FALSE);
         message.put("Message", String.format("El id %d no existe en la base de datos",id));
         return ResponseEntity.badRequest().body(message);
     }
     message.put("Success", Boolean.TRUE);
     message.put("responseUser", findId);
     return ResponseEntity.ok(message);

    }

   /* @PreAuthorize("hasRole('ADMINISTRATOR')")*/
    @PutMapping("/update/{id}")
    ResponseEntity updateUser(@PathVariable(required = false) Integer id, @RequestBody NewPersonDto user){

        Map<String,Object> message = new HashMap<>();
        Optional<User> findIdUser = userDAOService.findByIdP(id);

        if (findIdUser.isEmpty()){
            //throw new BadRequestExceptions("El id %d no existe en la base de datos");
            message.put("Success", Boolean.FALSE);
            message.put("Message", String.format("El id %d no existe en la base de datos",id));
            return ResponseEntity.badRequest().body(message);
        }

        User updateUser;
        Set<Rol> roles=new HashSet<>();

        updateUser = findIdUser.get();
        updateUser.setName(user.getName());
        updateUser.setLastname(user.getLastname());
        updateUser.setPhone(user.getPhone());
        updateUser.setEmail(user.getEmail());


        roles.add(rolDAOImp.findByNameRol(RolName.ROL_USER).get());

        if (user.getRoles().contains("administrator")) {

            roles.add(rolDAOImp.findByNameRol(RolName.ROL_ADMINISTRATOR).get());

        }
        updateUser.setRoles(roles);

        message.put("Success", Boolean.TRUE);
        message.put("responseUser", userDAOService.save(updateUser));
        return ResponseEntity.ok(message);


    }


    @PostMapping("/save")
    ResponseEntity<?> saveUser(@RequestBody NewPersonDto user){
        Map<String, Object> message = new HashMap<>();
        Optional<User> byUser = userDAOService.findByUser(user.getUser());
        Optional<User> byEmail = userDAOService.findByEmail(user.getEmail());

        if (byUser.isPresent()){

            //return new  ResponseEntity(new BadRequestExceptions("El nombre ya existe"), HttpStatus.BAD_REQUEST);
            message.put("Success", Boolean.FALSE);
            message.put("Message", String.format("El nombre de usuarios %s ya existe ",user.getUser()));

            return ResponseEntity.badRequest().body(message);
        }

        if (byEmail.isPresent()){

            // return new  ResponseEntity(new BadRequestExceptions("El correo ya existe"), HttpStatus.BAD_REQUEST);
            message.put("Success", Boolean.FALSE);
            message.put("Message", String.format("El email %s ya existe ",user.getEmail()));

            return ResponseEntity.badRequest().body(message);
        }

        User  saveUser = new User(null, user.getName(), user.getLastname(), user.getPhone(), user.getEmail(), user.getUser(), passwordEncoder.encode(user.getPassword()));

        Set<Rol> roles = new HashSet<>();

        roles.add(rolDAOImp.findByNameRol(RolName.ROL_USER).get());

        if (user.getRoles().contains("administrator")) {

            roles.add(rolDAOImp.findByNameRol(RolName.ROL_ADMINISTRATOR).get());

        }
        
        saveUser.setRoles(roles);

        message.put("Success", Boolean.TRUE);
        message.put("responseUser", userDAOService.save(saveUser));
        return ResponseEntity.ok(message);


    }

   /* @PreAuthorize("hasRole('ADMINISTRATOR')")*/
    @DeleteMapping("/delete/{id_n}")
    ResponseEntity<?> deleteUser (@PathVariable(value = "id_n", required = false) Integer id){

        Map<String, Object> message = new HashMap<>();

       Optional<User> findId = userDAOService.findByIdP(id);

       if (findId.isEmpty()){
           message.put("Success", Boolean.FALSE);
           message.put("Message", String.format("El id  %d no existe en la base de datos ",id));

           return ResponseEntity.badRequest().body(message);
       }

        message.put("Success", Boolean.TRUE);
        message.put("responseUser", userDAOService.deleteById(id));

        return ResponseEntity.ok(message);


    }
}
