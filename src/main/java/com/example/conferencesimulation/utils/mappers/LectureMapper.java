package com.example.conferencesimulation.utils.mappers;

import com.example.conferencesimulation.dto.LectureDto;
import com.example.conferencesimulation.model.Lecture;
import org.mapstruct.Mapper;

@Mapper
public interface LectureMapper {
    LectureDto reservationToReservationDto(Lecture lecture);
}
