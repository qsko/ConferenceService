package com.example.conferencesimulation.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalTime;

@Entity
public class Lecture {

    @Id
    private int lectureId;
    private Path path;
    private LocalTime time;
}
