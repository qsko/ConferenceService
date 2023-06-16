package com.example.conferencesimulation.mappers;

import com.example.conferencesimulation.dto.UserDto;
import com.example.conferencesimulation.model.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    UserDto userToUserDto(User user);
}
