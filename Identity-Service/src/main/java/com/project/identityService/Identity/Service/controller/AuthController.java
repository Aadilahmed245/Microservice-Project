package com.project.identityService.Identity.Service.controller;

import com.project.identityService.Identity.Service.dto.AuthRequest;
import com.project.identityService.Identity.Service.model.UserCredential;
import com.project.identityService.Identity.Service.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
    public String getToken(@RequestBody AuthRequest authRequest) {
        // To validate user is valid or not ( present in database or not)
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
        if (authenticate.isAuthenticated()) {
            return service.generateToken(authRequest.getUserName());
        } else {
            throw new RuntimeException("invalid access");
        }
    }
    @GetMapping("/validate")
    public String validateToken(@RequestParam String token)
    {
         service.validateToken(token);
         return "Token is valid";
    }

}
