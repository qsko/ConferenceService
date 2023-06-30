package com.example.conferencesimulation.model;

import java.util.HashMap;
import java.util.Map;

public class StatisticForPaths {
    private Map<Path, Double> statistics = new HashMap<>();

    public void putElements(Path path, double percent){

        int temp = (int)(percent*100.0);
        double shortDouble = ((double)temp)/100.0;
        statistics.put(path, shortDouble);
    }

    public Map<Path, Double> getStatistics() {
        return statistics;
    }

    public void getStatistics(HashMap<Path, Double> statistics) {
        this.statistics = statistics;
    }
}
