package com.example.conferencesimulation.services;
import com.example.conferencesimulation.model.Lecture;
import com.example.conferencesimulation.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.conferencesimulation.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public List<Lecture> getUserLectures(String login) {
        return null;
    }

    @Override
    public void signupForLecture(String login, String email, int lectureId) {

    }

    @Override
    public User registerUser(String login, String email) {
        User user = new User(login, email);
        return userRepository.save(user);
    }

    @Override
    public User updateUsersMail(String login, String newEmail) {

        User user = userRepository.findUserByLogin(login);
        user.setEmail(newEmail);
        userRepository.save(user);
        return user;
    }
}
