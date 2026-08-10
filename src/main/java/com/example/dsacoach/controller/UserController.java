package com.example.dsacoach.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dsacoach.DTO.RequestDTO.UserRequestDTO;
import com.example.dsacoach.DTO.ResponseDTO.UserResponseDTO;
import com.example.dsacoach.entity.User;
import com.example.dsacoach.service.UserService;

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
    public ResponseEntity<UserResponseDTO> addUser(@RequestBody  User user) 
    {
        UserResponseDTO savedUser = userService.registerUser(user.getEmail(),user.getUsername(),user.getPassword());
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    /*@PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@RequestBody UserRequestDTO user) 
    {
        UserResponseDTO entityUser = userService.login(user.getUsername(), user.getEmail(), user.getPassword());
        if(entityUser.isSuccess())
        {
            return new ResponseEntity<>(entityUser, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(entityUser, HttpStatus.BAD_REQUEST);
        }
    }*/
    


    @GetMapping("/getByUsername")
    public ResponseEntity<UserResponseDTO> getByUsername(@RequestBody UserRequestDTO user) 
    {
        UserResponseDTO searchedUser= userService.findByUsername(user.getUsername());
        return new ResponseEntity<>(searchedUser, HttpStatus.OK);
    }
    
    @GetMapping("/getByEmail")
    public ResponseEntity<UserResponseDTO> getByEmail(@RequestBody UserRequestDTO user) 
    {
        UserResponseDTO searchedUser= userService.findByEmail(user.getEmail());
        return new ResponseEntity<>(searchedUser, HttpStatus.OK);
    }

    @PutMapping("/update/username")
    public ResponseEntity<UserResponseDTO> updateUsername(@RequestBody UserRequestDTO user,@RequestParam String newUsername) 
    {
        UserResponseDTO updatedUser=userService.updateUsername(user.getUsername(), newUsername);
        return new ResponseEntity<>(updatedUser,HttpStatus.OK);
    }

    @PutMapping("/update/email")
    public ResponseEntity<UserResponseDTO> updateEmail(@RequestBody UserRequestDTO user,@RequestParam String newEmail) 
    {
        UserResponseDTO updatedUser=userService.updateEmail(user.getEmail(), newEmail);
        return new ResponseEntity<>(updatedUser,HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<UserResponseDTO> deleteUser(@RequestBody UserRequestDTO user)
    {
        UserResponseDTO deletedUser=userService.deleteUser(user.getEmail());
        return new ResponseEntity<>(deletedUser, HttpStatus.OK);
    }
}
