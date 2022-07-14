package com.claudio.coparmex.controllers;

import com.claudio.coparmex.exceptions.BadRequestExceptions;
import com.claudio.coparmex.models.entities.Partner;
import com.claudio.coparmex.models.entities.Rol;
import com.claudio.coparmex.models.entities.enumerators.RolName;
import com.claudio.coparmex.security.dto.JwtDto;
import com.claudio.coparmex.security.dto.LoginDto;
import com.claudio.coparmex.security.dto.NewPersonDto;
import com.claudio.coparmex.security.jwt.JwtProvider;
import com.claudio.coparmex.services.contracts.PartnerDAO;
import com.claudio.coparmex.services.implementations.PartnerDAOImp;
import com.claudio.coparmex.services.implementations.RolDAOImp;
import com.claudio.coparmex.services.implementations.UserDetailsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

   @Autowired
    PartnerDAO partnerDAOService;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;
   /* @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Partner partner, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            return new  ResponseEntity(new BadRequestExceptions("Campos requeridos"), HttpStatus.BAD_REQUEST);
        }

        if (partnerDAOImpService.existsPartnerByUser(partner.getUser())){

            return new  ResponseEntity(new BadRequestExceptions("El nombre ya existe"), HttpStatus.BAD_REQUEST);

        }

        if (partnerDAOImpService.existsByEmail(partner.getEmail())){

            return new  ResponseEntity(new BadRequestExceptions("El correo ya existe"), HttpStatus.BAD_REQUEST);
        }

        Partner savePartner = new Partner(null, partner.getName(), partner.getLastname(), partner.getPhone(),partner.getEmail(),
                partner.getPasess(), passwordEncoder.encode(partner.getPassword()), partner.getCompany(), partner.getAddress(), partner.getUser());

        Set<Rol> roles = new HashSet<>();

        roles.add(rolDAOImp.findByNameRol(RolName.ROL_PARTNER).get());

        if (partner.getRoles().contains("admin")){
            roles.add(rolDAOImp.findByNameRol(RolName.ROL_ADMINISTRATOR).get());
        }

        partner.setRoles(roles);

        partnerDAOImpService.save(partner);
        return new  ResponseEntity(new BadRequestExceptions("Usuario registrado"), HttpStatus.CREATED);
    }*/
    @PostMapping("/login")
    public  ResponseEntity<?> login(@RequestBody LoginDto loginDto, BindingResult bindingResult){

    /*    Optional<Partner> byUser = partnerDAOService.findByUser(loginDto.getUser());
        Optional<Partner> byPassword = partnerDAOService.findByPassword(loginDto.getPassword());*/

        Map<String, Object> message = new HashMap<>();

        if (bindingResult.hasErrors()){
            //return new  ResponseEntity(new BadRequestExceptions("Campos requeridos"), HttpStatus.BAD_REQUEST);
            message.put("Success", Boolean.FALSE);
            message.put("Message", String.format("Nombre de usuario o password incorrectos"));
            return ResponseEntity.badRequest().body(message);
        }
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUser(), loginDto.getPassword())) ;
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        message.put("Success", Boolean.TRUE);
        message.put("Data", jwtDto);
        return ResponseEntity.ok(message);

    }

}
