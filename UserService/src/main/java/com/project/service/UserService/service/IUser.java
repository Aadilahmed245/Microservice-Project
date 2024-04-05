package com.project.service.UserService.service;

import com.project.service.UserService.model.Users;

import java.util.List;

public interface IUser {
    boolean saveUser(Users user);

    List<Users> getAllUsers();

    Users findById(Integer id);
}
