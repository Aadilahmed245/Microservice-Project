package com.project.service.UserService.service;

import com.project.service.UserService.model.Users;
import com.project.service.UserService.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserImpl implements IUser{
    @Autowired
    private UserRepo userRepo;
    @Override
    public boolean saveUser(Users user) {
        Users save = userRepo.save(user);
        return save!=null;
    }

    @Override
    public List<Users> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public Users findById(Integer id) {
       return userRepo.findById(id).get();
    }
}
