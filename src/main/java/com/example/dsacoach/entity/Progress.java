package com.example.dsacoach.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(name="ProgressTab",columnNames = {"user_id","question_id"}))
public class Progress 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="question_id")
    private Question question;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    private boolean solved;
    
    protected Progress()
    {

    }

    public Progress(User user,Question question,boolean solved)
    {
        this.user=user;
        this.question=question;
        this.solved=solved;
    }

    public boolean isSolved()
    {
        return solved;
    }

    public User getUser()
    {
        return user;
    }

    public Integer getId()
    {
        return id;
    }

    public Question getQuestion()
    {
        return question;
    }

    public void setUser(User user)
    {
        this.user=user;
    }

    public void setQuestion(Question question)
    {
        this.question=question;
    }

    public void setSolved(boolean solved)
    {
        this.solved=solved;
    }
}
