package com.project.service.UserService.controller;

import com.project.service.UserService.model.Users;
import com.project.service.UserService.service.IUser;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Log4j2
@RequestMapping("/users")
public class UserController {
    @Autowired
    private IUser iUser;
    @PostMapping("/save-user")
    public ResponseEntity<String> saveUser(@RequestBody Users user)
    {
        boolean saved = iUser.saveUser(user);
        if(saved)
        {
            return ResponseEntity.status(HttpStatus.OK).body("Users saved Successfully");
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Users not saved!");
        }
    }
    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers()
    {
        log.info("reached here--------------1");
        List<Users>  usersList= iUser.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(usersList);

    }
    @GetMapping("/get-user-by-id")
    public ResponseEntity<Object> getUser(@RequestParam Integer id)
    {
        Users user = iUser.findById(id);
        if(user!=null)
        {
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.OK).body("User not found by the given id");
        }
    }
}
