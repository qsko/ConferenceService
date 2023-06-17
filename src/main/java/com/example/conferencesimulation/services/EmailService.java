package com.example.conferencesimulation.services;

import java.io.IOException;

public interface EmailService {

    void sendMail(String to, String message) throws IOException;
}
