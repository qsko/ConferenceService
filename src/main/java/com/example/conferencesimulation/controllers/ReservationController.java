package com.example.conferencesimulation.controllers;
import com.example.conferencesimulation.model.StatisticForLectures;
import com.example.conferencesimulation.model.StatisticForPaths;
import com.example.conferencesimulation.model.User;
import com.example.conferencesimulation.services.ReservationService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(value = "/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping(value = "/{login}/{email}/{lectureId}")
    public User signupForLecture(@PathVariable String login, @PathVariable String email, @PathVariable int lectureId) throws IOException {
        return reservationService.signupForLecture(login,email,lectureId);
    }

    @DeleteMapping(value = "/{login}/{lectureId}")
    public User cancelRegistrationForLecture(@PathVariable String login, @PathVariable int lectureId){
        return reservationService.cancelRegistrationForLecture(login,lectureId);
    }

    @GetMapping(value = "/statistics/lectures")
    public StatisticForLectures getStatisticForLectures(){
        return reservationService.getStatisticsForLectures();
    }

    @GetMapping(value = "/statistics/paths")
    public StatisticForPaths getStatisticForPath(){
        return reservationService.getStatisticsForPaths();
    }
}
