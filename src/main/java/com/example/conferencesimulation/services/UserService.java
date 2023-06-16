package com.example.conferencesimulation.services;

import com.example.conferencesimulation.dto.UserDto;
import com.example.conferencesimulation.exceptions.LoginConflictException;
import com.example.conferencesimulation.model.Lecture;
import com.example.conferencesimulation.model.User;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();
    List<Lecture> getUserLectures(String login);
    User signupForLecture(String login, String email, int lectureId);
    User registerUser(String login, String email) throws LoginConflictException;
    User updateUsersMail(String login, String newEmail);
}
