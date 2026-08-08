package com.example.dsacoach.service;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.dsacoach.DTO.ResponseDTO.UserResponseDTO;
import com.example.dsacoach.entity.User;
import com.example.dsacoach.repository.UserRepository;
import com.example.dsacoach.enumFolder.Role;

@Service
public class UserService 
{
      private final UserRepository userRepository;

      private final PasswordEncoder passwordEncoder;
      
      public UserService(UserRepository userRepository,PasswordEncoder passwordEncoder)
      {
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
      }

      //REGISTER
      public UserResponseDTO registerUser(String email,String username,String password)
      {
        if(userRepository.findByEmail(email)==null)
        {
          if(userRepository.findByUsername(username)==null)
          {
            String hashedPassword = passwordEncoder.encode(password);
            User user = new User(username,email,hashedPassword);
            user.setRole(Role.USER);
            userRepository.save(user);
            return new  UserResponseDTO(true, "User creation successful", user.getUsername(),user.getEmail(),user.getRole());
          }
          else
          {
            return new UserResponseDTO(false, "Could not create user since username is already taken", null,null,null);
          }
        }
        else
        {
          return new UserResponseDTO(false, "Provided email already assosiated with an account thus cannot create new user", null,null,null);
        }
      }

      //LOGIN
      /*public UserResponseDTO login(String username,String email,String password)
      {
        User user = userRepository.findByEmailOrUsername(email, username);
        if(user!=null)
        {
          if(passwordEncoder.matches(password,user.getPassword()))
          {
            return new UserResponseDTO(true, "User login successfull", user);
          }
          else
          {
            return new UserResponseDTO(false, "User login failed due to incorrect password", user);
          }
        }
        else
        {
          return new UserResponseDTO(false, "No user found with given username or email", user);
        }
      }*/
      
      public UserResponseDTO findByUsername(String username)
      {
        User user = userRepository.findByUsername(username);
        if(user!=null)
        {
          return new UserResponseDTO(true, "user entity found via username", user.getUsername(),user.getEmail(),user.getRole());
        }
        else
        {
          return new UserResponseDTO(false, "No user entity with given username found, thus search failed",null,null,null);
        }
      }

      public UserResponseDTO findByEmail(String email)
      {
        User user = userRepository.findByEmail(email);
        if(user!=null)
        {
          return new UserResponseDTO(true, "user entity found via email", user.getUsername(),user.getEmail(),user.getRole());
        }
        else
        {
          return new UserResponseDTO(false, "No user entity with given email found, thus search failed", null,null,null);
        }
      }

      public UserResponseDTO updateUsername(String oldUsername,String newUsername)
      {
        User user = userRepository.findByUsername(oldUsername);
        if(user==null)
        {
          return new UserResponseDTO(false, "No user with given username found thus cannot change username", null,null,null);
        }
        else
        {
          user.setUsername(newUsername);
          userRepository.save(user);
          return new UserResponseDTO(true, "username updated successfully", user.getUsername(),user.getEmail(),user.getRole());
        }
      }

      public UserResponseDTO updateEmail(String oldEmail,String newEmail)
      {
        User user = userRepository.findByEmail(oldEmail);
        if(user==null)
        {
          return new UserResponseDTO(false, "No user with given email found thus cannot change email", null,null,null);
        }
        else
        {
          user.setEmail(newEmail);
          userRepository.save(user);
          return new UserResponseDTO(true, "Email updated successfully", user.getUsername(),user.getEmail(),user.getRole());
        }
      }

      public UserResponseDTO deleteUser(String email)
      {
        User user=userRepository.findByEmail(email);
        if(user==null)
        {
          return new UserResponseDTO(false, "No user associated with given email thus deletion of account is invalid", null,null,null);
        }
        else
        {
          userRepository.delete(user);
          return new UserResponseDTO(true, "user deletion successful", user.getUsername(),user.getEmail(),user.getRole());
        }
      }
}
