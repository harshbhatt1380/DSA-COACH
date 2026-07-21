package com.example.dsacoach.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dsacoach.service.ProgressService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.dsacoach.DTO.RequestDTO.ProgressRequestDTO;
import com.example.dsacoach.DTO.ResponseDTO.ProgressResponseDTO;




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
}
