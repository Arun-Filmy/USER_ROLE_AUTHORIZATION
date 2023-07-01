package com.ROLEUSER.UR.entity;

import com.ROLEUSER.UR.dto.UserDto;
import com.ROLEUSER.UR.dto.UserRegistrationDto;
import com.ROLEUSER.UR.service.interf.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CustomUserDetails implements UserService {
    @Override
    public UserDto registerUser(UserRegistrationDto userDto) {
        return (UserDto) Collections.singleton(new SimpleGrantedAuthority(userDto.getRoleName()));
    }

    @Override
    public User getUserById(Long id) {
        return null;
    }

    @Override
    public List<UserDto> getAllUsers() {
        return null;
    }

    @Override
    public void updateUser(Long id, UserDto userDto) {

    }

    @Override
    public void deleteUser(Long id) {

    }

    @Override
    public User getUserByName(UserDto dto) {
        return null;
    }
}
