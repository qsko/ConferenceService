package com.example.conferencesimulation.repositories;

import com.example.conferencesimulation.model.Lecture;
import org.springframework.data.repository.CrudRepository;

public interface LectureRepository extends CrudRepository<Lecture, Integer> {
}
