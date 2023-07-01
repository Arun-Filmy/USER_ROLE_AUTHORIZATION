package com.ROLEUSER.UR.service.interf;

import com.ROLEUSER.UR.dto.UserDto;
import com.ROLEUSER.UR.dto.UserRegistrationDto;
import com.ROLEUSER.UR.entity.User;

import java.util.List;

public interface UserService {

    UserDto registerUser(UserRegistrationDto userDto);
    User getUserById(Long id);
    List<UserDto> getAllUsers();
    void updateUser(Long id, UserDto userDto);
    void deleteUser(Long id);


    User getUserByName(UserDto dto);
}
