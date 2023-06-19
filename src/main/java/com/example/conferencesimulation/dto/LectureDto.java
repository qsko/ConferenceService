package com.example.conferencesimulation.dto;

import com.example.conferencesimulation.model.Lecture;
import com.example.conferencesimulation.model.Path;

import java.time.LocalDateTime;
import java.util.Objects;

public class LectureDto {
    private Path path;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public LectureDto(Path path, LocalDateTime startDate, LocalDateTime endDate) {
        this.path = path;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LectureDto(Lecture lecture) {
        this.path = lecture.getPath();
        this.startDate = lecture.getStartDate();
        this.endDate = lecture.getEndDate();
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

    @Override
    public String toString() {
        return "Lecture{" +
                "path=" + path +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LectureDto that = (LectureDto) o;
        return path == that.path && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(path, startDate, endDate);
    }
}

