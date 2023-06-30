package com.example.conferencesimulation.model;

import com.example.conferencesimulation.dto.LectureDto;

import java.util.HashMap;
import java.util.Map;

public class StatisticForLectures {
    private Map<LectureDto, Double> statistics = new HashMap<>();

    public void putElements(LectureDto lecture, double percent){

        int temp = (int)(percent*100.0);
        double shortDouble = ((double)temp)/100.0;
        statistics.put(lecture, shortDouble);
    }

    public Map<LectureDto, Double> getStatistics() {
        return statistics;
    }

    public void setStatistics(Map<LectureDto, Double> statistics) {
        this.statistics = statistics;
    }
}
