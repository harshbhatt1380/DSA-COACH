package com.example.dsacoach.DTO.RequestDTO;

import com.example.dsacoach.enumFolder.Difficulty;

public class QuestionRequestDTO 
{
    Integer id;
    String title;
    Difficulty difficulty;
    
    QuestionRequestDTO(Integer id,String title,Difficulty difficulty)
    {
        this.id=id;
        this.title=title;
        this.difficulty=difficulty;
    }

    public void setId(Integer id)
    {
        this.id=id;
    }

    public void setTitle(String title)
    {
        this.title=title;
    }

    public void setDifficulty(Difficulty difficulty)
    {
        this.difficulty=difficulty;
    }

    public Integer getId()
    {
        return id;
    }

    public String getTitle()
    {
        return title;
    }

    public Difficulty getDifficulty()
    {
        return difficulty;
    }
}
