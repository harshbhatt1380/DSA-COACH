package com.example.dsacoach.entity;

import com.example.dsacoach.enumFolder.Difficulty;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Question 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String title;

    private String statement;

    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    public Question(String title,String statement,Difficulty difficulty)
    {
        this.title=title;
        this.statement=statement;
        this.difficulty=difficulty;
    }

    protected Question()
    {

    }

    public Difficulty getDifficulty()
    {
        return difficulty;
    }


    public String getStatement()
    {
        return statement;
    }

    public Integer getId()
    {
        return id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title=title;
    }

    public void setStatement(String statement)
    {
        this.statement=statement;
    }

    public void setDifficulty(Difficulty difficulty)
    {
        this.difficulty=difficulty;
    }
}
