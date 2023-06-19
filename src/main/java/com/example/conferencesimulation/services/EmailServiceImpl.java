package com.example.conferencesimulation.services;

import com.example.conferencesimulation.model.Lecture;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class EmailServiceImpl implements EmailService{
    @Override
    public void sendMail(String to, Lecture lecture) throws IOException {
        String message = "Successfully registered for lecture: " +
                lecture.formatForMail();
        String notification = LocalDateTime.now() + "," + to + "," + message +"\n";
        BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/notifications.txt"));
        writer.write(notification);
        writer.close();
    }
}
