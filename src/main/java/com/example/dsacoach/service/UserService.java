package com.example.dsacoach.service;

import org.springframework.stereotype.Service;

import com.example.dsacoach.entity.User;
import com.example.dsacoach.repository.UserRepository;

@Service
public class UserService 
{
      private final UserRepository userRepository;
      
      public UserService(UserRepository userRepository)
      {
        this.userRepository=userRepository;
      }

      public User addUser(User user)
      {
        return userRepository.save(user);
      } 
}
