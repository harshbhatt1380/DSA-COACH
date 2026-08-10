package com.example.dsacoach.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.dsacoach.DTO.ResponseDTO.QuestionResponseDTO;
import com.example.dsacoach.DTO.ResponseDTO.QuestionResponseList;
import com.example.dsacoach.entity.Question;
import com.example.dsacoach.enumFolder.Difficulty;
import com.example.dsacoach.service.QuestionService;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/questions")
public class QuestionController 
{
    private final QuestionService questionService;

    public QuestionController(QuestionService questionService)
    {
        this.questionService=questionService;
    }

    @GetMapping("/getById")
    public ResponseEntity<QuestionResponseDTO> findById(@RequestParam Integer id) 
    {
        QuestionResponseDTO result = questionService.findById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    

    @PutMapping("/changeDifficulty")
    public ResponseEntity<QuestionResponseDTO> changeDifficulty(@RequestParam Integer id,@RequestParam Difficulty difficulty) 
    {
        QuestionResponseDTO result = questionService.updateDifficulty(id, difficulty);
        return new ResponseEntity<QuestionResponseDTO>(result, HttpStatus.OK);
    }

    @PutMapping("/changeTitle")
    public ResponseEntity<QuestionResponseDTO> changeTitle(@RequestParam Integer id,@RequestParam String title) 
    {
        QuestionResponseDTO result = questionService.updateTitle(id, title);
        return new ResponseEntity<QuestionResponseDTO>(result, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<QuestionResponseDTO> deleteById(@RequestParam Integer id,@RequestParam String title)
    {
        QuestionResponseDTO result=questionService.deleteQuestion(id,title);
        return new ResponseEntity<QuestionResponseDTO>(result, HttpStatus.OK);
    }

    @GetMapping("/getByTitle")
    public ResponseEntity<QuestionResponseDTO> getByTitle(@RequestParam String title) 
    {
        QuestionResponseDTO result=questionService.findByTitle(title);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/getByDifficulty")
    public ResponseEntity<List<QuestionResponseList>> getQuestionsBasedOnDifficulty(@RequestParam Difficulty difficulty) 
    {
        ArrayList<QuestionResponseList> result = questionService.getQuestionByDifficulty(difficulty);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    

    @GetMapping("/all")
    public ResponseEntity<List<QuestionResponseList>> getAllQuestions() 
    {
        ArrayList<QuestionResponseList> result =  questionService.getAllQuestions();
        return new ResponseEntity<List<QuestionResponseList>>(result, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<QuestionResponseDTO> addQuestion(@RequestBody Question question) 
    {
        QuestionResponseDTO savedQuestion = questionService.saveQuestion(question);

        return new ResponseEntity<>(savedQuestion,HttpStatus.CREATED);
    }   
}
