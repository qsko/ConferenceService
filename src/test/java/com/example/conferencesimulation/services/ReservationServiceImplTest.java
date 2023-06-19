package com.example.conferencesimulation.services;

import com.example.conferencesimulation.dto.LectureDto;
import com.example.conferencesimulation.exceptions.SignupForLectureException;
import com.example.conferencesimulation.exceptions.StatisticsException;
import com.example.conferencesimulation.model.*;
import com.example.conferencesimulation.repositories.LectureRepository;
import com.example.conferencesimulation.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservationServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private LectureRepository lectureRepository;
    @Mock
    private EmailService emailService;
    @InjectMocks
    private ReservationServiceImpl reservationService;

    private final String login = "login";
    private final String mail = "mail";
    private final int lectureId11 = 11;
    private final int lectureId12 = 12;
    private final int lectureId32 = 32;
    private final int lectureId33 = 33;
    private final LocalDateTime startDate1  = LocalDateTime.of(LocalDate.of(2023, 6, 1), LocalTime.of(10, 0));
    private final LocalDateTime endDate1  = LocalDateTime.of(LocalDate.of(2023, 6, 1), LocalTime.of(11, 45));
    private final LocalDateTime startDate2  = LocalDateTime.of(LocalDate.of(2023, 6, 1), LocalTime.of(14, 0));
    private final LocalDateTime endDate2  = LocalDateTime.of(LocalDate.of(2023, 6, 1), LocalTime.of(15, 45));
    private final Lecture lecture11 = new Lecture(lectureId11, Path.PATH1, startDate1, endDate1, new ArrayList<User>());
    private final Lecture lecture12 = new Lecture(lectureId12, Path.PATH2, startDate1, endDate1, new ArrayList<User>());
    private final Lecture lecture32 = new Lecture(lectureId32, Path.PATH2, startDate2, endDate2, new ArrayList<User>());
    private final Lecture lecture33 = new Lecture(lectureId33, Path.PATH3, startDate2, endDate2, new ArrayList<User>());
    private final User user1 = new User(1,login, mail, new ArrayList<>());

    @Test
    void cancelRegistrationForLecture_Success() {

        user1.setLectures( new ArrayList<>(List.of(lecture11)));

        when(userRepository.findUserByLogin(login)).thenReturn(Optional.of(user1));
        when(lectureRepository.findById(lectureId11)).thenReturn(Optional.of(lecture11));
        when(userRepository.save(user1)).thenReturn(user1);
        User returnedUser = reservationService.cancelRegistrationForLecture(login, lectureId11);
        assertEquals(returnedUser.getLectures().size(), 0);
    }

    @Test
    void signupForLecture_LectureFullyBooked_Fail() throws IOException {
        ArrayList<User> users = getArrayListWithNUsers(5);
        lecture11.setUsers(users);
        when(lectureRepository.findById(lectureId11)).thenReturn(Optional.of(lecture11));

        Exception ex = assertThrows(SignupForLectureException.class,()->{
            reservationService.signupForLecture(user1.getLogin(), user1.getEmail(), lectureId11);
        });
        assertEquals("There's no more place in this lecture", ex.getMessage());
    }
    @Test
    void signupForLecture_UserHasLectureAtTime_Fail() {

       user1.setLectures(List.of(lecture12));

        when(userRepository.findUserByLogin(login)).thenReturn(Optional.of(user1));
        when(lectureRepository.findById(lectureId11)).thenReturn(Optional.of(lecture11));

        Exception ex = assertThrows(SignupForLectureException.class,()->{
            reservationService.signupForLecture(user1.getLogin(), user1.getEmail(), lectureId11);
        });
        assertEquals("User is already signed up for lecture at this time", ex.getMessage());
    }

    @Test
    void signupForLecture_EmailSent() throws IOException {

        when(userRepository.findUserByLogin(login)).thenReturn(Optional.of(user1));
        when(lectureRepository.findById(lectureId11)).thenReturn(Optional.of(lecture11));
        reservationService.signupForLecture(user1.getLogin(), user1.getEmail(), lectureId11);

        verify(emailService,times(1)).sendMail(user1.getEmail(), lecture11);
    }

    @Test
    void signupForLecture_Success() throws IOException {

        when(userRepository.findUserByLogin(login)).thenReturn(Optional.of(user1));
        when(userRepository.save(user1)).thenReturn(user1);
        when(lectureRepository.findById(lectureId11)).thenReturn(Optional.of(lecture11));

        User user =  reservationService.signupForLecture(user1.getLogin(), user1.getEmail(), lectureId11);

        assertEquals(user.getLectures().get(0), lecture11);
    }

    @Test
    void getStatisticsForLectures_Success() {
        ArrayList<User> users = getArrayListWithNUsers(2);
        lecture11.setUsers(users);
        users = getArrayListWithNUsers(2);
        lecture12.setUsers(users);
        users = getArrayListWithNUsers(4);
        lecture32.setUsers(users);

        List<Lecture> lectures = List.of(lecture11, lecture12, lecture32);
        when(lectureRepository.findAll()).thenReturn(lectures);

        StatisticForLectures statistic = reservationService.getStatisticsForLectures();
        assertEquals(statistic.getStatistics().get(new LectureDto(lecture11)), 0.25d);
        assertEquals(statistic.getStatistics().get(new LectureDto(lecture12)), 0.25d);
        assertEquals(statistic.getStatistics().get(new LectureDto(lecture32)), 0.5d);
    }

    @Test
    void getStatisticsForLectures_NoLectures_Fail() {
        List<Lecture> lectures = List.of(lecture11, lecture12, lecture32);
        when(lectureRepository.findAll()).thenReturn(lectures);

        Exception ex = assertThrows(StatisticsException.class,()->{
            reservationService.getStatisticsForLectures();
        });
        assertEquals("No reservation made in system, can't make statistics", ex.getMessage());
    }

    @Test
    void getStatisticsForPaths_Success() {
        ArrayList<User> users = getArrayListWithNUsers(3);
        lecture11.setUsers(users);
        users = getArrayListWithNUsers(3);
        lecture12.setUsers(users);
        users = getArrayListWithNUsers(4);
        lecture33.setUsers(users);

        List<Lecture> lectures = List.of(lecture11, lecture12, lecture33);
        when(lectureRepository.findAll()).thenReturn(lectures);

        StatisticForPaths statistic = reservationService.getStatisticsForPaths();
        assertEquals(statistic.getStatistics().get(Path.PATH1), 0.3d);
        assertEquals(statistic.getStatistics().get(Path.PATH2), 0.3d);
        assertEquals(statistic.getStatistics().get(Path.PATH3), 0.4d);
    }

    @Test
    void getStatisticsForPaths_NoLectures_Fail() {
        List<Lecture> lectures = List.of(lecture11, lecture12, lecture32);
        when(lectureRepository.findAll()).thenReturn(lectures);

        Exception ex = assertThrows(StatisticsException.class,()->{
            reservationService.getStatisticsForPaths();
        });
        assertEquals("No reservation made in system, can't make statistics", ex.getMessage());
    }

    private ArrayList<User> getArrayListWithNUsers(int n){
        ArrayList<User> users = new ArrayList<>();
        users.add(new User());
        for(int i =0; i <n-1 ;i ++){
            users.add(new User());
        }
        return users;
    }
}