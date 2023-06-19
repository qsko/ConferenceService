package com.example.conferencesimulation.model;

import com.example.conferencesimulation.dto.LectureDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashMap;

public class StatisticForLectures {
    private HashMap<LectureDto, Double> statistics = new HashMap<>();

    public void putElements(LectureDto lecture, double percent){

        int temp = (int)(percent*100.0);
        double shortDouble = ((double)temp)/100.0;
        statistics.put(lecture, shortDouble);
    }

    public HashMap<LectureDto, Double> getStatistics() {
        return statistics;
    }

    public void setStatistics(HashMap<LectureDto, Double> statistics) {
        this.statistics = statistics;
    }
}
