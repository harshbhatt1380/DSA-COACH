package com.example.dsacoach.MyExceptions;

public class EmailAlreadyTakenException extends RuntimeException
{
    public EmailAlreadyTakenException(String message)
    {
        super(message);
    }
}
