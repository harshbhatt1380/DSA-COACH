package com.example.dsacoach.DTO.RequestDTO;

public class ProgressRequestDTO 
{
    private String username;
    private String questionTitle;
    private boolean solved;
    
    public ProgressRequestDTO()
    {

    }
    
    public void setUsername(String username)
    {
        this.username = username;
    }
    
    public void setQuestionTitle(String questionTitle)
    {
        this.questionTitle = questionTitle;
    }

    public void setSolved(boolean solved)
    {
        this.solved = solved;
    }

    public String getUsername()
    {
        return username;
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
