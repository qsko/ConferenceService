package com.example.conferencesimulation.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
public class Lecture {

    @Id
    @Column(name = "id")
    private int lectureId;
    private Path path;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    @ManyToMany
    @JoinTable(
            name = "CONFERENCE_USERS_LECTURES",
            joinColumns = @JoinColumn(name = "LECTURE_ID"),
            inverseJoinColumns = @JoinColumn(name = "USERID"))
    private List<User> users;

    public Lecture() {
    }

    public int getLectureId() {
        return lectureId;
    }

    public void setLectureId(int lectureId) {
        this.lectureId = lectureId;
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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Lecture{" +
                "lectureId=" + lectureId +
                ", path=" + path +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", users=" + users +
                '}';
    }
}
