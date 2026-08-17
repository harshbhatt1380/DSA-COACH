package com.example.dsacoach.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dsacoach.DTO.RequestDTO.EmailRequestDTO;
import com.example.dsacoach.DTO.RequestDTO.UserRequestDTO;
import com.example.dsacoach.DTO.ResponseDTO.LoginResponseDTO;
import com.example.dsacoach.DTO.ResponseDTO.UserResponseDTO;
import com.example.dsacoach.service.UserService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/users")
public class UserController 
{
    private final UserService userService;
    
    public UserController(UserService userService)
    {
        this.userService=userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> addUser(@Valid @RequestBody  UserRequestDTO user) 
    {
        UserResponseDTO savedUser = userService.registerUser(user.getEmail(),user.getUsername(),user.getPassword());
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody UserRequestDTO user) 
    {
        String token = userService.login(user.getUsername(), user.getEmail(), user.getPassword());
        return new ResponseEntity<>(new LoginResponseDTO(token), HttpStatus.OK);
    }
    


    @GetMapping("/getByUsername")
    public ResponseEntity<UserResponseDTO> getByUsername(@NotBlank @RequestParam String user) 
    {
        UserResponseDTO searchedUser= userService.findByUsername(user);
        return new ResponseEntity<>(searchedUser, HttpStatus.OK);
    }

    @PutMapping("/update/username")
    public ResponseEntity<UserResponseDTO> updateUsername(@NotBlank @RequestParam String newUsername) 
    {
        UserResponseDTO updatedUser=userService.updateUsername(newUsername);
        return new ResponseEntity<>(updatedUser,HttpStatus.OK);
    }

    @PutMapping("/update/email")
    public ResponseEntity<UserResponseDTO> updateEmail(@Valid @RequestBody EmailRequestDTO email) 
    {
        UserResponseDTO updatedUser=userService.updateEmail(email.getEmail());
        return new ResponseEntity<>(updatedUser,HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<UserResponseDTO> deleteUser()
    {
        UserResponseDTO deletedUser=userService.deleteUser();
        return new ResponseEntity<>(deletedUser, HttpStatus.OK);
    }
}
