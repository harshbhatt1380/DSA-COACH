package com.example.dsacoach.MyExceptions;

public class QuestionNotFoundException extends RuntimeException 
{ 
    public QuestionNotFoundException(String message)
    {
        super(message);
    }     
}
