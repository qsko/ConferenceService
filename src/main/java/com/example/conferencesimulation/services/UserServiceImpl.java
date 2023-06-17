package com.example.conferencesimulation.services;
import com.example.conferencesimulation.dto.LectureDto;
import com.example.conferencesimulation.dto.UserDto;
import com.example.conferencesimulation.exceptions.LoginConflictException;
import com.example.conferencesimulation.exceptions.SignupForLectureException;
import com.example.conferencesimulation.mappers.LectureMapper;
import com.example.conferencesimulation.mappers.UserMapper;
import com.example.conferencesimulation.model.Lecture;
import com.example.conferencesimulation.model.User;
import com.example.conferencesimulation.repositories.LectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.conferencesimulation.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private LectureMapper lectureMapper;

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = (List<User>) userRepository.findAll();
        return users.stream().map(user -> userMapper.userToUserDto(user)).toList();
    }

    @Override
    public List<LectureDto> getUserLectures(String login) {
        User user = userRepository.findUserByLogin(login).orElseThrow();
        return user.getLectures().stream().map(lecture -> lectureMapper.reservationToReservationDto(lecture)).toList();
    }

    @Override
    public User cancelRegistrationForLecture(String login, int lectureId) {

        User user = userRepository.findUserByLogin(login).orElseThrow();
        Lecture lecture = lectureRepository.findById(lectureId).orElseThrow();
        user.deleteLecture(lecture);
        return userRepository.save(user);
    }

    @Override
    public User signupForLecture(String login, String email, int lectureId) throws IOException {

        Lecture lecture = lectureRepository.findById(lectureId).orElseThrow();
        List<User> users = lecture.getUsers();
        if(users.size() > 4){
            throw new SignupForLectureException("There's no more place in this lecture");
        }
        User user = userRepository.findUserByLogin(login).orElseThrow();
        List<Lecture> lectures = user.getLectures();

        for(Lecture l: lectures){
            if(lecture.getStartDate().equals(l.getStartDate()))
                throw new SignupForLectureException("User is already signed up for lecture at this time");
        }

        user.addLecture(lecture);
        emailService.sendMail(user.getEmail(), "Succesfuly registered for lecture: " +
                lecture.formatForMail());
        return userRepository.save(user);
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
