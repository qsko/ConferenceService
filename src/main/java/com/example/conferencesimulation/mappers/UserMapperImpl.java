package com.example.conferencesimulation.mappers;

import com.example.conferencesimulation.dto.UserDto;
import com.example.conferencesimulation.model.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
public class UserMapperImpl implements  UserMapper{
    @Override
    public UserDto userToUserDto(User user) {
        if(user == null){
            return null;
        }
        return new UserDto(user.getLogin(), user.getEmail());

    }
}
