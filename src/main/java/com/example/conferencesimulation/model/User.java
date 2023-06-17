package com.example.conferencesimulation.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;


import java.util.List;

@Entity
@Table(name = "conference_users")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private int userID;
    @Column(unique=true)
    private String login;
    private String email;
    @ManyToMany
    @JsonIgnoreProperties("users")
    @JoinTable(
            name = "CONFERENCE_USERS_LECTURES",
            joinColumns = @JoinColumn(name = "USERID"),
            inverseJoinColumns = @JoinColumn(name = "LECTURE_ID"))
    private List<Lecture> lectures;

    public User() {

    }

    public User(String login, String email) {
        this.login = login;
        this.email = email;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Lecture> getLectures() {
        return lectures;
    }

    public void setLectures(List<Lecture> lectures) {
        this.lectures = lectures;
    }

    public void addLecture(Lecture lecture){
        this.lectures.add(lecture);
    }

    public void deleteLecture(Lecture lecture) { this.lectures.remove(lecture); }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", lectures=" + lectures +
                '}';
    }
}
