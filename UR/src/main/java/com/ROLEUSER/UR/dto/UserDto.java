package com.ROLEUSER.UR.dto;

import com.ROLEUSER.UR.entity.Role;
import com.ROLEUSER.UR.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String username;
    private String password;
    private Set<RoleDto> roles;

    public static UserDto fromEntity(User user) {
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());

        Set<RoleDto> roleDtos = user.getRoles().stream()
                .map((Role role) -> RoleDto.fromEntity(Optional.ofNullable(role)))
                .collect(Collectors.toSet());
        userDto.setRoles(roleDtos);

        return userDto;
    }
}
