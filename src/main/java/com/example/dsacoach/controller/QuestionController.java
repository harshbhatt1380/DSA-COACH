package com.example.dsacoach.controller;

import com.example.dsacoach.repository.QuestionRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.dsacoach.entity.Question;
import com.example.dsacoach.service.QuestionService;

import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/questions")
public class QuestionController 
{
    private final QuestionRepository questionRepository;
    private final QuestionService questionService;

    public QuestionController(QuestionService questionService, QuestionRepository questionRepository)
    {
        this.questionService=questionService;
        this.questionRepository = questionRepository;
    }

    @GetMapping("/getByTitle")
    public ResponseEntity<QuestionResponseDTO> getByTitle(@RequestParam String title) 
    {
        Question ques=questionRepository.findByTitle(title);
        if(ques!=null)
        {
            return new QuestionResponseDTO();
        }
        
    }
    

    @GetMapping("/all")
    public List<Question> getAllQuestions() 
    {
        return questionService.getAllQuestions();
    }

    @PostMapping("/add")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question) 
    {
        Question savedQuestion = questionService.saveQuestion(question);
        return new ResponseEntity<>(savedQuestion,HttpStatus.CREATED);
    }   
}
