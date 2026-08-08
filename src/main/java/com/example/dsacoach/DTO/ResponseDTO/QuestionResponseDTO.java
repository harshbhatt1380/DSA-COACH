package com.example.dsacoach.DTO.ResponseDTO;

import com.example.dsacoach.enumFolder.Difficulty;

public class QuestionResponseDTO 
{
    private final boolean success; 
    private final String message;  
    private final String title;
    private final Difficulty difficulty;
    
    public QuestionResponseDTO(boolean success,String message,String title,Difficulty difficulty)
    {
        this.title=title;
        this.difficulty=difficulty;
        this.message=message;
        this.success=success;
    }

    public String getTitle()
    {
        return title;
    }

    public String getMessage()
    {
        return message;
    }

    public Difficulty getDifficulty()
    {
        return difficulty;
    }

    public boolean isSuccess()
    {
        return success;
    }
}
