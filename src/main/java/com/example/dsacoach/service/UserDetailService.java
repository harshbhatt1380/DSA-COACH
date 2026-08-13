package com.example.dsacoach.service;

import java.util.ArrayList;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.example.dsacoach.MyExceptions.UserNotFoundException;
import com.example.dsacoach.Security.UserDetail;
import com.example.dsacoach.entity.User;
import com.example.dsacoach.repository.UserRepository;

@Service
public class UserDetailService implements UserDetailsService
{
    private final UserRepository userRepository;

    public UserDetailService(UserRepository userRepository)
    {
        this.userRepository=userRepository;
    }

    public UserDetail loadUserByUsername(String username)
    {
        User user=userRepository.findByUsername(username);
        if(user==null)
        {
            throw new UserNotFoundException("No user found with given username");
        }
        else
        {
            ArrayList<GrantedAuthority> al = new ArrayList<>();
            al.add(user.getRole());
            return new UserDetail(user.getUsername(),user.getPassword(),al);
        }
    }
}
