package com.example.dsacoach.MyExceptions;

public class UsernameAlreadyTakenException extends RuntimeException
{
    public UsernameAlreadyTakenException(String message)
    {
        super(message);
    }
}
