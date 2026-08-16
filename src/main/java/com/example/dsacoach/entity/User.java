package com.example.dsacoach.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import com.example.dsacoach.enumFolder.Role;

@Entity
@Table(name="users")
public class User 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;  

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    @NotBlank
    @Email
    (
        message = "Please provide a valid email address"
    )
    private String email;

    @NotBlank(message = "Password cannot be blank")
    private String password;

    
    @Enumerated(EnumType.STRING)
    private Role role;

    protected User()
    {

    }
    public User(String username,String email, String password)
    {
        this.username=username;
        this.email=email;
        this.password=password;
    }

    public Integer getId()
    {
        return id;
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

    public void setPassword(String password)
    {
        this.password=password;
    }

    public void setUsername(String username)
    {
        this.username=username;
    }

    public void setEmail(String email)
    {
        this.email=email;
    }

    public void setRole(Role role)
    {
        this.role=role;
    }
    public Role getRole()
    {
        return role;
    }
}
