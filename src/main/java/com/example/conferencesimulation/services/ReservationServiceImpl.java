package com.example.conferencesimulation.services;

import com.example.conferencesimulation.dto.LectureDto;
import com.example.conferencesimulation.exceptions.SignupForLectureException;
import com.example.conferencesimulation.exceptions.StatisticsException;
import com.example.conferencesimulation.model.*;
import com.example.conferencesimulation.repositories.LectureRepository;
import com.example.conferencesimulation.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LectureRepository lectureRepository;
    @Autowired
    private EmailService emailService;

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
        emailService.sendMail(user.getEmail(), lecture);
        return userRepository.save(user);
    }


    @Override
    public StatisticForLectures getStatisticsForLectures() {

        List<Lecture> lectures = (List<Lecture>) lectureRepository.findAll();
        int totalNumberOfParticipants = getTotalNumberOfParticipants(lectures);

        StatisticForLectures statistic = new StatisticForLectures();
        for(Lecture lecture: lectures){
            double percent = (double)lecture.getUsers().size()/totalNumberOfParticipants;
            statistic.putElements(new LectureDto(lecture), percent);
        }
        return statistic;
    }

    @Override
    public StatisticForPaths getStatisticsForPaths() {

        List<Lecture> lectures = (List<Lecture>) lectureRepository.findAll();
        int totalNumberOfParticipants = getTotalNumberOfParticipants(lectures);

        StatisticForPaths statistic = new StatisticForPaths();

        for(Path path: Path.values()){
            int pathParticipants = lectures.stream()
                    .filter(l -> l.getPath() == path)
                    .map(l -> l.getUsers().size())
                    .mapToInt(Integer::intValue)
                    .sum();
            double percent = (double) pathParticipants/ totalNumberOfParticipants;
            statistic.putElements(path,percent);
        }

        return statistic;
    }

    private int getTotalNumberOfParticipants(List<Lecture> lectures){
        int totalNumberOfParticipants = 0;

        for(Lecture lecture: lectures){
            totalNumberOfParticipants += lecture.getUsers().size();
        }
        if(totalNumberOfParticipants == 0)
            throw new StatisticsException("No reservation made in system, can't make statistics");

        return totalNumberOfParticipants;
    }
}
