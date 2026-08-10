package com.example.dsacoach.MyExceptions;

import com.example.dsacoach.DTO.ResponseDTO.ErrorResponse;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler 
{

    @ExceptionHandler(value = UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleUserNotFound(UserNotFoundException ex)
    {
        return new ErrorResponse(false, ex.getMessage());
    }

    @ExceptionHandler(value = UsernameAlreadyTakenException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleUsernameAlreadyTaken(UsernameAlreadyTakenException ex)
    {
        return new ErrorResponse(false, ex.getMessage());
    }

    @ExceptionHandler(value = EmailAlreadyTakenException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleEmailAlreadyTaken(EmailAlreadyTakenException ex)
    {
        return new ErrorResponse(false, ex.getMessage());
    }

    @ExceptionHandler(value=QuestionAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleQuestionAlreadyExists(QuestionAlreadyExistsException ex)
    {
        return new ErrorResponse(false, ex.getMessage());
    }

    @ExceptionHandler(value=QuestionNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleQuestionNotFound(QuestionNotFoundException ex)
    {
        return new ErrorResponse(false, ex.getMessage());
    }

    @ExceptionHandler(value=QuestionTitleAlreadyTakenException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleQuestionTitleAlreadyTaken(QuestionTitleAlreadyTakenException ex)
    {
        return new ErrorResponse(false, ex.getMessage());
    }
}
