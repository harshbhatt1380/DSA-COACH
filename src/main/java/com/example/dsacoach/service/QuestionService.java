package com.example.dsacoach.service;

import org.springframework.stereotype.Service;

import com.example.dsacoach.entity.Question;
import com.example.dsacoach.repository.QuestionRepository;

import java.util.List;


@Service
public class QuestionService 
{
    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository)
    {
        this.questionRepository = questionRepository;
    }

    public List<Question> getAllQuestions()
    {
        return questionRepository.findAll();
    }

    public Question saveQuestion(Question question)
    {
        return questionRepository.save(question);
    }
}
