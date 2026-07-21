package com.example.dsacoach.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dsacoach.entity.Progress;
import com.example.dsacoach.service.ProgressService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
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
    public  ResponseEntity<Progress> addProgress(@RequestParam String username,@RequestParam String questionTitle,@RequestParam boolean solved) 
    {   
        return progressService.addProgress(username, questionTitle,solved);
    }
}
