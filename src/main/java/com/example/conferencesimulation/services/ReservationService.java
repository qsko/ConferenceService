package com.example.conferencesimulation.services;

import com.example.conferencesimulation.model.StatisticForLectures;
import com.example.conferencesimulation.model.StatisticForPaths;
import com.example.conferencesimulation.model.User;

import java.io.IOException;

public interface ReservationService {

    User cancelRegistrationForLecture(String login, int lectureId);
    User signupForLecture(String login, String email, int lectureId) throws IOException;
    StatisticForLectures getStatisticsForLectures();
    StatisticForPaths getStatisticsForPaths();
}
