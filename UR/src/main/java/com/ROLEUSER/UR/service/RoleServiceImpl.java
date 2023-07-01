package com.ROLEUSER.UR.service;

import com.ROLEUSER.UR.dto.RoleDto;
import com.ROLEUSER.UR.entity.Role;
import com.ROLEUSER.UR.repo.RoleRepository;
import com.ROLEUSER.UR.service.interf.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findRoleByName(String name) {
        return roleRepository.findByName(name);
    }

//    @Override
//    public RoleDto getRoleById(Long id) {
//        System.out.println("Inside roleService");
//        if (id == null) {
//            Role newRole = new Role();
//             newRole.setName("USER");
//             newRole.setId(123L);
//            Role savedRole = roleRepository.save(newRole);
//            return RoleDto.fromEntity(Optional.of(savedRole));
//        } else {
//            Optional<Role> role = roleRepository.findById(id);
//            return (RoleDto) role.orElse(null);
//        }
//    }
//
//
//    @Override
//    public List<RoleDto> getAllRoles() {
//        List<Role> roles = roleRepository.findAll();
//        return roles.stream()
//                .map((Role role) -> RoleDto.fromEntity(Optional.ofNullable(role)))
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public void createRole(RoleDto roleDto) {
//        Role role = new Role();
//        role.setName(roleDto.getName());
//        roleRepository.save(role);
//    }
//
//    @Override
//    public void updateRole(Long id, RoleDto roleDto) {
//        Role role = roleRepository.findById(id)
//                .orElseThrow(() -> new NoSuchElementException("Role not found."));
//
//        role.setName(roleDto.getName());
//        roleRepository.save(role);
//    }
//
//    @Override
//    public void deleteRole(Long id) {
//        Role role = roleRepository.findById(id)
//                .orElseThrow(() -> new NoSuchElementException("Role not found."));
//
//        roleRepository.delete(role);
//    }
}
