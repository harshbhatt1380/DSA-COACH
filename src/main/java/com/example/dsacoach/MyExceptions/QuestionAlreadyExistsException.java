package com.example.dsacoach.MyExceptions;

public class QuestionAlreadyExistsException extends RuntimeException 
{
    public QuestionAlreadyExistsException(String message)
    {
        super(message);
    }    
}
