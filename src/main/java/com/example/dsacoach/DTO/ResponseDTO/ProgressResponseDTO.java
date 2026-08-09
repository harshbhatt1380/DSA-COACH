package com.example.dsacoach.DTO.ResponseDTO;

public class ProgressResponseDTO 
{
    private final boolean success;
    private final String message;
    private final String username;
    private final String questionTitle;
    private final boolean solved;
    
    public ProgressResponseDTO(boolean success,String message,String username,String questionTitle,boolean solved)
    {
        this.success=success;
        this.message=message;
        this.username=username;
        this.questionTitle=questionTitle;
        this.solved=solved;
    }

    public boolean getSuccess()
    {
        return success;
    }

    public String getMessage()
    {
        return message;
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
