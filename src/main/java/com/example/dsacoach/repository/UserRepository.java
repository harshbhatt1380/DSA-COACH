package com.example.dsacoach.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.dsacoach.entity.User;
import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> 
{
    User findByUsername(String username);
    List<User> findByEmail(String email);
}
