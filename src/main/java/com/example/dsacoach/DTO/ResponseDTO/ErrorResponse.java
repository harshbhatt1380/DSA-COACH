package com.example.dsacoach.DTO.ResponseDTO;

public class ErrorResponse 
{
    public ErrorResponse(boolean success,String message)
    {
        this.success=success;
        this.message=message;
    }
    private final boolean  success;
    private final String message;
    
    public boolean isSuccess()
    {
        return success;
    }

    public String getMessage()
    {
        return message;
    }
}
