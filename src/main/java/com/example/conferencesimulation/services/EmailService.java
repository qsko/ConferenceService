package com.example.conferencesimulation.services;

import com.example.conferencesimulation.model.Lecture;

import java.io.IOException;

public interface EmailService {

    void sendMail(String to, Lecture lecture) throws IOException;
}
