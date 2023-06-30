package com.example.conferencesimulation.services;

import com.example.conferencesimulation.exceptions.LoginConflictException;
import com.example.conferencesimulation.model.User;
import com.example.conferencesimulation.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private final String login1 = "login1";
    private final String mail1 = "mail1";
    private final String mail2 = "mail2";
    private final User user1 = new User(1,login1, mail1, new ArrayList<>());


    @Test
    void getUserLectures_noUserWithLogin_Fail() {
        //given
        //when
        when(userRepository.findUserByLogin(login1)).thenReturn(Optional.empty());
        //then
        Exception ex = assertThrows(NoSuchElementException.class, ()-> userService.getUserLectures(login1));
        assertEquals(ex.getMessage(), "No value present");
    }

    @Test
    void registerUser_LoginAlreadyTakenAndMailIsDifferent_Fail() {
        //given
        //when
        when(userRepository.findUserByLogin(login1)).thenReturn(Optional.of(user1));
        //then
        Exception ex = assertThrows(LoginConflictException.class, ()-> userService.registerUser(login1,
                mail2));
        assertEquals("Login is already taken", ex.getMessage());
    }

    @Test
    void registerUser_LoginAlreadyTakenAndMailIsSame_Success() {
        //given
        //when
        when(userRepository.findUserByLogin(login1)).thenReturn(Optional.of(user1));
        //then
        User user = userService.registerUser(login1, mail1);
        assertEquals(user, user1);
    }

    @Test
    void registerUser_LoginIsFree_Success() {
        //given
        //when
        when(userRepository.findUserByLogin(login1)).thenReturn(Optional.empty());

        when(userRepository.save(Mockito.any(User.class)))
                .thenAnswer(i -> user1);
        //then
        User user = userService.registerUser(login1, mail1);
        assertEquals(user, user1);
    }

    @Test
    void updateUsersMail_NoUserWithLogin_Fail() {
        //given

        //when
        when(userRepository.findUserByLogin(login1)).thenReturn(Optional.empty());
        //then
        Exception ex = assertThrows(NoSuchElementException.class, ()-> userService.updateUsersMail(login1, mail1));
        assertEquals(ex.getMessage(), "No value present");
    }

    @Test
    void updateUsersMail_Success() {
        //given

        //when
        when(userRepository.findUserByLogin(login1)).thenReturn(Optional.of(user1));
        when(userRepository.save(Mockito.any(User.class)))
                .thenAnswer(i -> i.getArguments()[0]);
        //then
        User user = userService.updateUsersMail(login1,mail2);
        assertEquals(user.getEmail(), mail2);
    }
}