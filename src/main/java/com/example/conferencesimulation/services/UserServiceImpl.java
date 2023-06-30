package com.example.conferencesimulation.services;
import com.example.conferencesimulation.dto.LectureDto;
import com.example.conferencesimulation.dto.UserDto;
import com.example.conferencesimulation.exceptions.LoginConflictException;
import com.example.conferencesimulation.utils.mappers.LectureMapper;
import com.example.conferencesimulation.utils.mappers.UserMapper;
import com.example.conferencesimulation.model.User;
import com.example.conferencesimulation.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final LectureMapper lectureMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, LectureMapper lectureMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.lectureMapper = lectureMapper;
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = (List<User>) userRepository.findAll();
        return users.stream().map(userMapper::userToUserDto).toList();
    }

    @Override
    public List<LectureDto> getUserLectures(String login) {
        User user = userRepository.findUserByLogin(login).orElseThrow();
        return user.getLectures().stream().map(lectureMapper::reservationToReservationDto).toList();
    }

    @Override
    public User registerUser(String login, String email) throws LoginConflictException {

        Optional<User> user = userRepository.findUserByLogin(login);
        if(user.isEmpty()) {
            User newUser = new User(login, email);
            return userRepository.save(newUser);
        } else if(!user.get().getEmail().equals(email)){
                throw new LoginConflictException("Login is already taken");
        }
        return user.get();
    }

    @Override
    public User updateUsersMail(String login, String newEmail) {

        User user = userRepository.findUserByLogin(login).orElseThrow();
        user.setEmail(newEmail);
        return userRepository.save(user);
    }
}
