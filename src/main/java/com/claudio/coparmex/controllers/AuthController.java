package com.claudio.coparmex.controllers;

import com.claudio.coparmex.security.dto.JwtDto;
import com.claudio.coparmex.security.dto.LoginDto;
import com.claudio.coparmex.security.jwt.JwtProvider;
import com.claudio.coparmex.services.contracts.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

   @Autowired
   UserDAO userDAOService;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/login")
    public  ResponseEntity<?> login(@RequestBody LoginDto loginDto, BindingResult bindingResult){

           Map<String, Object> message = new HashMap<>();

        if (bindingResult.hasErrors()){

            message.put("Success", Boolean.FALSE);
            message.put("responseLogin", String.format("Nombre de usuario o password incorrectos"));
            return ResponseEntity.badRequest().body(message);
        }
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUser(), loginDto.getPassword())) ;
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        message.put("Success", Boolean.TRUE);
        message.put("responseLogin", jwtDto);
        return ResponseEntity.ok(message);

    }

}
