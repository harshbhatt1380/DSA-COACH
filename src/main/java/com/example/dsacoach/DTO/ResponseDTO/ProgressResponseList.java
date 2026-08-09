package com.example.dsacoach.DTO.ResponseDTO;

public class ProgressResponseList 
{

    private final String questionTitle;
    private final boolean solved;

    public ProgressResponseList(String title,boolean solved)
    {
        questionTitle=title;
        this.solved=solved;
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
