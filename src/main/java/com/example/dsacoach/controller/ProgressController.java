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
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/questionProgress")
    public ResponseEntity<ProgressResponseDTO> getQuestionProgress(@RequestParam String username,@RequestParam Integer qid) 
    {
        ProgressResponseDTO result = progressService.getQuestionProgress(username, qid);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @GetMapping("/allquestionProgress")
    public ResponseEntity<ArrayList<ProgressResponseList>> getAllQuestionProgress(@RequestParam String username) 
    {
        ArrayList<ProgressResponseList> result = progressService.getAllQuestionProgress(username);
        return new ResponseEntity<>(result, HttpStatus.OK); 
    }  
}
