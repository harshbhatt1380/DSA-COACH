package com.example.dsacoach.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.dsacoach.entity.User;

public interface UserRepository extends JpaRepository<User,Integer> 
{
    User findByUsername(String username);
    User findByEmail(String email);

    User findByEmailAndUsername(String email,String username);

    User findByEmailOrUsername(String email,String username);
}
