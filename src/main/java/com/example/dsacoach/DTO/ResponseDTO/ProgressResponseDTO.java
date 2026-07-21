package com.example.dsacoach.DTO.ResponseDTO;

import com.example.dsacoach.entity.Progress;

public class ProgressResponseDTO 
{
    private final boolean success;
    private final String message;
    private final Progress progress;
    
    public ProgressResponseDTO(boolean success,String message,Progress progress)
    {
        this.success=success;
        this.message=message;
        this.progress=progress;
    }

    public boolean getSuccess()
    {
        return success;
    }

    public String getMessage()
    {
        return message;
    }

    public Progress getProgress()
    {
        return progress;
    }
}
