package com.example.conferencesimulation.controllers;

import com.example.conferencesimulation.dto.LectureDto;
import com.example.conferencesimulation.dto.UserDto;
import com.example.conferencesimulation.model.Lecture;
import com.example.conferencesimulation.repositories.LectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/conferencePlan")
public class LectureController {

    @Autowired
    private LectureRepository lectureRepository;

    @GetMapping
    public List<LectureDto> getLectures(){
        List<Lecture> lectures = (List<Lecture>) lectureRepository.findAll();
        return lectures.stream().map(LectureDto::new).toList();
    }
}
