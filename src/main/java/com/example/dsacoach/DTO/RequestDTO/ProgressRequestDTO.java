package com.example.dsacoach.DTO.RequestDTO;

import jakarta.validation.constraints.NotBlank;

public class ProgressRequestDTO 
{
    @NotBlank
    private String questionTitle;
    
    private boolean solved;
    
    public ProgressRequestDTO()
    {

    }
    
    
    public void setQuestionTitle(String questionTitle)
    {
        this.questionTitle = questionTitle;
    }

    public void setSolved(boolean solved)
    {
        this.solved = solved;
    }

    public String getQuestionTitle()
    {
        return questionTitle;
    }

    public boolean isSolved()
    {
        return solved;
    }
}
