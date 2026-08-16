package com.example.dsacoach.service;


import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.dsacoach.DTO.ResponseDTO.UserResponseDTO;
import com.example.dsacoach.MyExceptions.EmailAlreadyTakenException;
import com.example.dsacoach.MyExceptions.InvalidCredentialsException;
import com.example.dsacoach.MyExceptions.UserNotFoundException;
import com.example.dsacoach.MyExceptions.UsernameAlreadyTakenException;
import com.example.dsacoach.entity.User;
import com.example.dsacoach.repository.UserRepository;
import com.example.dsacoach.enumFolder.Role;

@Service
public class UserService 
{
      private final UserRepository userRepository;

      private final PasswordEncoder passwordEncoder;

      private final JwtService jwtService;
      
      public UserService(UserRepository userRepository,PasswordEncoder passwordEncoder,JwtService jwtService)
      {
        this.jwtService=jwtService;
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
            user.setRole(Role.ROLE_USER);
            userRepository.save(user);
            return new  UserResponseDTO(true, "User creation successful", user.getUsername(),user.getEmail(),user.getRole());
          }
          else
          {
            throw new UsernameAlreadyTakenException("Could not create user since username is already taken");
            //return new UserResponseDTO(false, "Could not create user since username is already taken", null,null,null);
          }
        }
        else
        {
          throw new EmailAlreadyTakenException("Provided email already assosiated with an account thus cannot create new user");
          //return new UserResponseDTO(false, "Provided email already assosiated with an account thus cannot create new user", null,null,null);
        }
      }

      //LOGIN
      public String login(String username,String email,String password)
      {
        User user = userRepository.findByEmailOrUsername(email, username);
        if(user==null)
        {
          throw new UserNotFoundException("No user found with given username or email thus login failed");
        }
        if(!passwordEncoder.matches(password,user.getPassword()))
          {
            throw new InvalidCredentialsException("Incorrect password thus login failed");
          }
          return jwtService.generateToken(user.getUsername());
      }
      
      public UserResponseDTO findByUsername(String username)
      {
        User user = userRepository.findByUsername(username);
        if(user!=null)
        {
          return new UserResponseDTO(true, "user entity found via username", user.getUsername(),user.getEmail(),user.getRole());
        }
        else
        {
          throw new UserNotFoundException("No user entity with given username found, thus search via username failed");
          //return new UserResponseDTO(false, "No user entity with given username found, thus search failed",null,null,null);
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
          throw new UserNotFoundException("No user entity with given email found, thus search via Email failed");
          //return new UserResponseDTO(false, "No user entity with given email found, thus search failed", null,null,null);
        }
      }

      public UserResponseDTO updateUsername(String newUsername)
      {
        String oldUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(oldUsername);
        if(user==null)
        {
          throw new UserNotFoundException("No user with given username found thus cannot change username");
        }
        else
        {
          User checkUser=userRepository.findByUsername(newUsername);
          if(checkUser!=null)
          {
            throw new UsernameAlreadyTakenException(newUsername+" username is already taken by another user thus action of changing username failed");
          }
          else
          {
            user.setUsername(newUsername);
            userRepository.save(user);
            return new UserResponseDTO(true, "username updated successfully", user.getUsername(),user.getEmail(),user.getRole());
          }
        }
      }

      public UserResponseDTO updateEmail(String newEmail)
      {
        String username =SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username);
        if(user==null)
        {
          throw new UserNotFoundException("No user found in database with jwt provided username thus cannot change email");
        }
        else
        {
          User checkingUser=userRepository.findByEmail(newEmail);
          if(checkingUser!=null)
          {
            throw new EmailAlreadyTakenException(newEmail+"already taken by another user,thus updation of user email failed");
          }
          else
          {
            user.setEmail(newEmail);
            userRepository.save(user);
            return new UserResponseDTO(true, "Email updated successfully", user.getUsername(),user.getEmail(),user.getRole());
          }
        }
      }

      public UserResponseDTO deleteUser()
      {
        String username =SecurityContextHolder.getContext().getAuthentication().getName();
        User user=userRepository.findByUsername(username);
        if(user==null)
        {
          throw new UserNotFoundException("No user associated with given email thus deletion of account is invalid");
          //return new UserResponseDTO(false, "No user associated with given email thus deletion of account is invalid", null,null,null);
        }
        else
        {
          userRepository.delete(user);
          return new UserResponseDTO(true, "user deletion successful", user.getUsername(),user.getEmail(),user.getRole());
        }
      }
}
