package com.project.identityService.Identity.Service.repository;

import com.project.identityService.Identity.Service.model.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserCredentialRepo extends JpaRepository<UserCredential,Integer> {
    Optional<UserCredential> findByName(String userName);

}
