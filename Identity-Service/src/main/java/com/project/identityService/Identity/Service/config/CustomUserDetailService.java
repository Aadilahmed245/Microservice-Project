package com.project.identityService.Identity.Service.config;

import com.project.identityService.Identity.Service.model.UserCredential;
import com.project.identityService.Identity.Service.repository.UserCredentialRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomUserDetailService implements UserDetailsService {
    // we create custom user detail for authentication manager inside generate token api to verify user is valid
    // or not
    @Autowired
    private UserCredentialRepo repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserCredential> credential = repository.findByName(username);
        return credential.map(CustomUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("user not found with name :" + username));
    }
}
