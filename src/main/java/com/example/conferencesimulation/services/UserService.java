package com.example.conferencesimulation.services;

import com.example.conferencesimulation.dto.LectureDto;
import com.example.conferencesimulation.dto.UserDto;
import com.example.conferencesimulation.exceptions.LoginConflictException;
import com.example.conferencesimulation.model.User;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();
    List<LectureDto> getUserLectures(String login);
    User registerUser(String login, String email) throws LoginConflictException;
    User updateUsersMail(String login, String newEmail);
}
