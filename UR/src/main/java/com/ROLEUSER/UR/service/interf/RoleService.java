package com.ROLEUSER.UR.service.interf;

import com.ROLEUSER.UR.dto.RoleDto;
import com.ROLEUSER.UR.entity.Role;

import java.util.List;

public interface RoleService {
    Role findRoleByName(String name);
//    RoleDto getRoleById(Long id);
//    List<RoleDto> getAllRoles();
//    void createRole(RoleDto roleDto);
//    void updateRole(Long id, RoleDto roleDto);
//    void deleteRole(Long id);
}
