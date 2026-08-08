package com.example.dsacoach.DTO.ResponseDTO;

import com.example.dsacoach.enumFolder.Role;
public class UserResponseDTO 
{
    private final boolean success;
    private final String message;
    //User user;
    private final String username;
    private final String email;
    private final Role role; 
    
    public UserResponseDTO(boolean success,String message,String username,String email,Role role)
    {
        this.success=success;
        this.message=message;
        this.username=username;
        this.email=email;
        this.role=role;
    }


    public String getMessage()
    {
        return message;
    }

    public boolean isSuccess()
    {
        return success; 
    }

    public Role getRole()
    {
        return role;
    }

    public String getUsername()
    {
        return username;
    }

    public String getEmail()
    {
        return email;
    }
}
