package com.example.conferencesimulation.mappers;

import com.example.conferencesimulation.dto.LectureDto;
import com.example.conferencesimulation.model.Lecture;
import org.springframework.stereotype.Component;

@Component
public class LectureMapperImpl implements LectureMapper{
    @Override
    public LectureDto reservationToReservationDto(Lecture lecture) {
        if(lecture == null){
            return null;
        }
        return new LectureDto(lecture.getPath(), lecture.getStartDate(), lecture.getEndDate());
    }
}
