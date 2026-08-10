package com.example.dsacoach.MyExceptions;

public class QuestionTitleAlreadyTakenException extends RuntimeException 
{
    public QuestionTitleAlreadyTakenException(String message)
    {
        super(message);
    }
}
