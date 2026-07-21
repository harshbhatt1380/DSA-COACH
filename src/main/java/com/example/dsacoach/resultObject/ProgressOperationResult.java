package com.example.dsacoach.resultObject;

import com.example.dsacoach.entity.Progress;

public class ProgressOperationResult 
{
    private final boolean success;
    private final String message;
    private final Progress progress;
    
    public ProgressOperationResult(boolean success,String message,Progress progress)
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
