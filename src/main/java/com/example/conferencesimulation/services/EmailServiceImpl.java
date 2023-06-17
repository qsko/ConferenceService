package com.example.conferencesimulation.services;

import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class EmailServiceImpl implements EmailService{
    @Override
    public void sendMail(String to, String message) throws IOException {

        String notification = LocalDateTime.now() + "," + to + "," + message +"\n";
        BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/notifications.txt"));
        writer.write(notification);
        writer.close();
    }
}
