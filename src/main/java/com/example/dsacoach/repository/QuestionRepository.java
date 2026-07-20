package com.example.dsacoach.repository;
import com.example.dsacoach.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.dsacoach.enumFolder.Difficulty;
import java.util.List;

public interface QuestionRepository extends JpaRepository<Question,Integer> 
{
    Question findByTitle(String title);
    List<Question> findByDifficulty(Difficulty difficulty);    
}
