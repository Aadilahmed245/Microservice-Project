package com.project.identityService.Identity.Service.controller;

import com.project.identityService.Identity.Service.dto.AuthRequest;
import com.project.identityService.Identity.Service.model.UserCredential;
import com.project.identityService.Identity.Service.service.AuthService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService service;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public String addNewUser(@RequestBody UserCredential user)
    {
        return service.saveUser(user);
    }
    @PostMapping("/token")
    public ResponseEntity<String> getToken(@RequestBody AuthRequest authRequest) {
        Authentication authenticate=null;
        try {
             authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));

            }catch (AuthenticationException e){
             return ResponseEntity.status(HttpStatus.OK).body("Please Enter a valid username/password!!!");
        }
        String token=  service.generateToken(authRequest.getUserName(),authRequest.getUserId());
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam String token)
    {
         service.validateToken(token);
         return "Token is valid";
    }



}
