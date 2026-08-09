package com.example.dsacoach.repository;

import java.util.ArrayList;
import java.util.List;

import com.example.dsacoach.entity.Question;
import com.example.dsacoach.entity.User;
import com.example.dsacoach.entity.Progress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgressRepository extends JpaRepository<Progress,Integer> 
{
    List<Progress> findByUser(User user);

    Progress findByUserAndQuestion(User user,Question question);

    //for leaderboard community feature
    List<Progress> findByQuestion(Question question);
}
