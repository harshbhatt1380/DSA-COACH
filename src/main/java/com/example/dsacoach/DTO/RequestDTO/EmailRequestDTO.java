package com.example.dsacoach.DTO.RequestDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class EmailRequestDTO 
{
    @NotBlank(message = "Email cannot be empty")
    @Email
    (
        message = "Please provide a valid email address",
        regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$"
    )
    private String email;

    public EmailRequestDTO(String email)
    {
        this.email=email;
    }

    protected EmailRequestDTO()
    {
        
    }

    public void setEmail(String email)
    {
        this.email=email;
    }
    
    public String getEmail()
    {
        return email;
    }
}
