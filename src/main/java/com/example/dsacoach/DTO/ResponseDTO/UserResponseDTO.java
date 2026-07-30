package com.example.dsacoach.DTO.ResponseDTO;

import com.example.dsacoach.entity.User;
public class UserResponseDTO 
{
    private final boolean success;
    private final String message;
    User user; 
    
    public UserResponseDTO(boolean success,String message,User user)
    {
        this.success=success;
        this.message=message;
        this.user=user;
    }

    public User getUser()
    {
        return user;
    }

    public String getMessage()
    {
        return message;
    }

    public boolean isSuccess()
    {
        return success; 
    }
}
