package com.example.dsacoach.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dsacoach.service.ProgressService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.dsacoach.DTO.RequestDTO.ProgressRequestDTO;
import com.example.dsacoach.DTO.ResponseDTO.ProgressResponseDTO;
import com.example.dsacoach.DTO.ResponseDTO.ProgressResponseList;
import com.example.dsacoach.MyExceptions.UserNotFoundException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;





@RestController
@RequestMapping("/progress")
public class ProgressController 
{
    private final ProgressService progressService;
    
    public ProgressController(ProgressService progressService)
    {
        this.progressService=progressService;
    }

    @PostMapping("/add")
    public  ResponseEntity<ProgressResponseDTO> addProgress(@RequestBody ProgressRequestDTO progress) 
    {   
        ProgressResponseDTO result=progressService.addProgress(progress.getUsername(),progress.getQuestionTitle(),progress.isSolved());
        if(result.getSuccess())
        {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/questionProgress")
    public ResponseEntity<ProgressResponseDTO> getQuestionProgress(@RequestParam String username,@RequestParam Integer qid) 
    {
        ProgressResponseDTO result = progressService.getQuestionProgress(username, qid);
        if(result.getSuccess())
        {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/allquestionProgress")
    public ResponseEntity<ArrayList<ProgressResponseList>> getAllQuestionProgress(@RequestParam String username) 
    {
        ArrayList<ProgressResponseList> result;
        try 
        {
           result = progressService.getAllQuestionProgress(username);    
        } 
        catch (UserNotFoundException e) 
        {
            System.out.println("Caught Exception "+e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } 
        return new ResponseEntity<>(result, HttpStatus.OK); 
    }  
}
