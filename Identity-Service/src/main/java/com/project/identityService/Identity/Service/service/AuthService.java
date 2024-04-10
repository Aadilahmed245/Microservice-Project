package com.project.identityService.Identity.Service.service;

import com.project.identityService.Identity.Service.model.UserCredential;
import com.project.identityService.Identity.Service.repository.UserCredentialRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserCredentialRepo userCredentialRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public String saveUser(UserCredential credential)
    {
        credential.setPassword(passwordEncoder.encode(credential.getPassword()));
        userCredentialRepo.save(credential);
        return "user added in database successfylly";
    }
    public String generateToken(String username)
    {
        return jwtService.generateToken(username);
    }
    public void validateToken(String token)
    {
        jwtService.validateToken(token);
    }
}
