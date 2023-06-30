package com.example.conferencesimulation.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
public class Lecture {

    @Id
    @Column(name = "id")
    private int lectureId;
    private Path path;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    @ManyToMany
    @JsonIgnoreProperties("lectures")
    @JoinTable(
            name = "CONFERENCE_USERS_LECTURES",
            joinColumns = @JoinColumn(name = "LECTURE_ID"),
            inverseJoinColumns = @JoinColumn(name = "USERID"))
    private List<User> users;

    public Lecture() {
    }

    public Lecture(int lectureId, Path path, LocalDateTime startDate, LocalDateTime endDate, List<User> users) {
        this.lectureId = lectureId;
        this.path = path;
        this.startDate = startDate;
        this.endDate = endDate;
        this.users = users;
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

    public String formatForMail(){
        return "Lecture{" +
                "lectureId=" + lectureId +
                ", path=" + path +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lecture lecture = (Lecture) o;
        return lectureId == lecture.lectureId && path == lecture.path && startDate.equals(lecture.startDate) && endDate.equals(lecture.endDate) && Objects.equals(users, lecture.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lectureId);
    }
}
