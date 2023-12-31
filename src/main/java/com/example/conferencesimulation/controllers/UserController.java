package com.example.conferencesimulation.controllers;
import com.example.conferencesimulation.dto.LectureDto;
import com.example.conferencesimulation.dto.UserDto;
import com.example.conferencesimulation.exceptions.LoginConflictException;
import com.example.conferencesimulation.model.User;
import org.springframework.web.bind.annotation.*;
import com.example.conferencesimulation.services.UserServiceImpl;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService){
        this.userService = userService;
    }

    @GetMapping
    public List<UserDto> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping(value = "/{login}/{email}")
    public User registerUser(@PathVariable String login, @PathVariable String email) throws LoginConflictException {
        return userService.registerUser(login, email);
    }

    @PutMapping(value = "/{login}/{newEmail}")
    public User updateUserEmail(@PathVariable String login, @PathVariable String newEmail) {
        return userService.updateUsersMail(login, newEmail);
    }

    @GetMapping(value = "/{login}/reservations")
    public List<LectureDto> getUserReservation(@PathVariable String login) {
        return userService.getUserLectures(login);
    }
}
