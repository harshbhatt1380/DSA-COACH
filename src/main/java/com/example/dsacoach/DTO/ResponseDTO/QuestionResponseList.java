package com.example.dsacoach.DTO.ResponseDTO;

import com.example.dsacoach.enumFolder.Difficulty;

public class QuestionResponseList 
{
    private final String title;
    private final Difficulty difficulty;
    
    public QuestionResponseList(String title,Difficulty difficulty)
    {
        this.title=title;
        this.difficulty=difficulty;
    }

    public String getTitle()
    {
        return title;
    }

    public Difficulty getDifficulty()
    {
        return difficulty;
    }
}
