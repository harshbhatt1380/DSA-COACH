package com.example.dsacoach.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class User 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;  


    private String username;

    private String email;

    //private int tuptup;

    protected User()
    {

    }
    public User(String username,String email)//,int t
    {
        this.username=username;
        this.email=email;
        //this.tuptup=t;
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

    /*public int getTuptup()
    {
        return tuptup;
    }*/

    public void setUsername(String username)
    {
        this.username=username;
    }

    public void setEmail(String email)
    {
        this.email=email;
    }

    /*public void setTuptup(int tp)
    {
        this.tuptup=tp;
    }*/

}
