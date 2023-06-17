package com.example.conferencesimulation.dto;

import com.example.conferencesimulation.model.Path;

import java.time.LocalDateTime;

public class LectureDto {
    private Path path;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public LectureDto(Path path, LocalDateTime startDate, LocalDateTime endDate) {
        this.path = path;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
}

