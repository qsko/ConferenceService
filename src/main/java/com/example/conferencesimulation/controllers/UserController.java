package com.example.conferencesimulation.controllers;

import com.example.conferencesimulation.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.conferencesimulation.services.UserServiceImpl;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;


    @GetMapping
    public List<User> getAllUsers(){
        System.out.println("AAAa");
        return userService.getAllUsers();
    }

    @PostMapping(value = "/{login}/{email}")
    public User registerUser(@PathVariable String login, @PathVariable String email){
        return userService.registerUser(login, email);
    }

    @PutMapping(value = "/{login}/{newEmail}")
    public User updateUserEmail(@PathVariable String login, @PathVariable String newEmail){
        return userService.updateUsersMail(login, newEmail);
    }
}
