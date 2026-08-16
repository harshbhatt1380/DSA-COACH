package com.example.dsacoach.DTO.RequestDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserRequestDTO 
{
    @NotBlank(message = "Username cannot be empty/blank")
    private String username;
    
    @NotBlank(message = "Email address cannot be empty/blank")
    @Email
    (
        message = "Please provide a valid email address",
        regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$"
    )
    private String email;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8,message="Password must be atleast 8 characters")
    private String password;

    public UserRequestDTO()
    {
        
    }

    public void setUsername(String username)
    {
        this.username=username;
    }

    public void setEmail(String email)
    {
        this.email=email;
    }

    public void setPassword(String password)
    {
        this.password=password;
    }

    public String getUsername()
    {
        return username;
    }

    public String getEmail()
    {
        return email;
    }

    public String getPassword()
    {
        return password;
    }
}
