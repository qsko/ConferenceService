package com.example.conferencesimulation.services;

import com.example.conferencesimulation.model.Lecture;
import com.example.conferencesimulation.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    List<Lecture> getUserLectures(String login);
    void signupForLecture(String login, String email, int lectureId);
    User registerUser(String login, String email);
    User updateUsersMail(String login, String newEmail);
}
