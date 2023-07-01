package com.ROLEUSER.UR.dto;

import com.ROLEUSER.UR.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto extends Role {
    private Long id;
    private String name;

    public static RoleDto fromEntity(Optional<Role> role) {
        RoleDto roleDto = new RoleDto();
        roleDto.setId(role.get().getId());
        roleDto.setName(role.get().getName());
        return roleDto;
    }
}
