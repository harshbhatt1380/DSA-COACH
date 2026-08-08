package com.example.dsacoach.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.dsacoach.DTO.ResponseDTO.QuestionResponseDTO;
import com.example.dsacoach.entity.Question;
import com.example.dsacoach.enumFolder.Difficulty;
import com.example.dsacoach.service.QuestionService;

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

    @PutMapping("/changeDifficulty")
    public ResponseEntity<QuestionResponseDTO> changeTitle(@RequestParam Integer id,@RequestParam Difficulty difficulty) 
    {
        Question ques = questionService.updateDifficulty(id, difficulty);
        if(ques!=null)
        {
            return new ResponseEntity<>(new QuestionResponseDTO(true, "Difficulty of question changed successfully!", ques.getTitle(), ques.getDifficulty()), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(new QuestionResponseDTO(false, "Could not find any question with provided id and title in the database, thus updation of Difficulty failed", null, null), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/changeTitle")
    public ResponseEntity<QuestionResponseDTO> changeTitle(@RequestParam Integer id,@RequestParam String title) 
    {
        Question ques = questionService.updateTitle(id, title);
        if(ques!=null)
        {
            return new ResponseEntity<>(new QuestionResponseDTO(true, "Title of question changed successfully!", ques.getTitle(), ques.getDifficulty()), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(new QuestionResponseDTO(false, "Could not find any question with provided id and title in the database thus updation of Title failed", null, null), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<QuestionResponseDTO> deleteById(@RequestParam Integer id,@RequestParam String title)
    {
        Question deletedQuestion=questionService.deleteQuestion(id,title);
        if(deletedQuestion!=null)
        {
            return new ResponseEntity<>(new QuestionResponseDTO(true, "Question deleted successfully", deletedQuestion.getTitle(), deletedQuestion.getDifficulty()), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(new QuestionResponseDTO(false, "Could not find question thus deletion failed", null, null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getByTitle")
    public ResponseEntity<QuestionResponseDTO> getByTitle(@RequestParam String title) 
    {
        Question ques=questionService.findByTitle(title);
        if(ques!=null)
        {
            return new ResponseEntity<>(new QuestionResponseDTO(true, "Question found with the same title as  provided by user", ques.getTitle(), ques.getDifficulty()), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(new QuestionResponseDTO(false, "No question found with the same title as  provided by user", null, null), HttpStatus.BAD_REQUEST);
        }
    }
    

    @GetMapping("/all")
    public List<Question> getAllQuestions() 
    {
        return questionService.getAllQuestions();
    }

    @PostMapping("/add")
    public ResponseEntity<QuestionResponseDTO> addQuestion(@RequestBody Question question) 
    {
        Question savedQuestion = questionService.saveQuestion(question);
        if(savedQuestion!=null)
        {
            return new ResponseEntity<>(new QuestionResponseDTO(true,"Question saved successfully" ,savedQuestion.getTitle() ,savedQuestion.getDifficulty()),HttpStatus.CREATED);
        }
        else
        {
            return new ResponseEntity<>(new QuestionResponseDTO(false,"Question could not be saved due to duplicate entry,thus question already exists in database" ,null ,null),HttpStatus.BAD_REQUEST);
        }
    }   
}
