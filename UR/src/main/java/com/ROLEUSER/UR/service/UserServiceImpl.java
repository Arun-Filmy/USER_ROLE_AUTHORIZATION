package com.ROLEUSER.UR.service;

import com.ROLEUSER.UR.dto.UserDto;
import com.ROLEUSER.UR.dto.UserRegistrationDto;
import com.ROLEUSER.UR.entity.Role;
import com.ROLEUSER.UR.entity.User;
import com.ROLEUSER.UR.repo.UserRepository;
import com.ROLEUSER.UR.service.interf.RoleService;
import com.ROLEUSER.UR.service.interf.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder encoder;

    public UserServiceImpl(UserRepository userRepository, RoleService roleService, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.encoder = encoder;
    }

    @Override
    public UserDto registerUser(UserRegistrationDto userRegistrationDto) {
        User existingUser = userRepository.findByUsername(userRegistrationDto.getUsername());
        if (existingUser != null) {
            throw new IllegalArgumentException("Username already exists.");
        }

        User newUser = new User();
        newUser.setUsername(userRegistrationDto.getUsername());
        newUser.setPassword(encoder.encode(userRegistrationDto.getPassword()));
        System.out.println("newUser is set");
        Role role = roleService.findRoleByName(userRegistrationDto.getRoleName());
        System.out.println("Comes back");
        if (role == null) {
            throw new NoSuchElementException("Role not found.");
        }
        System.out.println("Checked role of user");
        newUser.getRoles().add(role);

        User savedUser = userRepository.save(newUser);
        return UserDto.fromEntity(savedUser);
    }


    @Override
    public User getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found."));

        return user;
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                    .map(UserDto::fromEntity)
                .collect(Collectors.toList());

    }

    @Override
    public void updateUser(Long id, UserDto userDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found."));

        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());

        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found."));

        userRepository.delete(user);
    }

    @Override
    public User getUserByName(UserDto dto) {
        User byUsername = userRepository.findByUsername(dto.getUsername());
        if(byUsername.getPassword() == dto.getPassword()){
            return byUsername;
        }
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return (UserDetails) user;
    }
}
