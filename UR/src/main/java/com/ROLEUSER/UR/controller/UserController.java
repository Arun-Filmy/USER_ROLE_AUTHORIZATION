package com.ROLEUSER.UR.controller;

import com.ROLEUSER.UR.dto.UserDto;
import com.ROLEUSER.UR.dto.UserRegistrationDto;
import com.ROLEUSER.UR.entity.User;
import com.ROLEUSER.UR.service.interf.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    public UserController(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserRegistrationDto userRegistrationDto) {
        UserDto registeredUser = userService.registerUser(userRegistrationDto);
        return ResponseEntity.ok(registeredUser);
    }

    @GetMapping("/login")
    public ResponseEntity<String> loginUser(@RequestParam String username, @RequestParam String password) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // Check if the user is already authenticated
        if (authentication != null && authentication.isAuthenticated()) {
            return ResponseEntity.ok("You are already logged in");
        }
        // Attempt to authenticate the user
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
            authentication = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return ResponseEntity.ok("You are successfully logged in");
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }
    @GetMapping("/fetch/{id}")
    public String getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return "The details are " + HttpStatus.OK + "  " + user.toString();
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<UserDto>> getAllUsers(@RequestParam String username) {
//        List<UserDto> users = userService.getAllUsers();
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Void> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
//        userService.updateUser(id, userDto);
//        return ResponseEntity.ok().build();
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
//        userService.deleteUser(id);
//        return ResponseEntity.ok().build();
//    }
}

