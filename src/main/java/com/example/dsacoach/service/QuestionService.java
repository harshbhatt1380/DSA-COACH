package com.example.dsacoach.service;

import org.springframework.stereotype.Service;

import com.example.dsacoach.entity.Question;
import com.example.dsacoach.enumFolder.Difficulty;
import com.example.dsacoach.repository.QuestionRepository;

import java.util.List;
import java.util.Optional;


@Service
public class QuestionService 
{
    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository)
    {
        this.questionRepository = questionRepository;
    }

    public Question findByTitle(String title)
    {
        Question ques = questionRepository.findByTitle(title);
        return ques;
    }

    public Question findById(Integer id)
    {
        Optional<Question> searchedQuestion=questionRepository.findById(id);
        if(searchedQuestion.isPresent())
        {
            Question ques=searchedQuestion.get();
            return ques;
        }
        else
        {
            return null;
        }
    }

    public Question updateDifficulty(Integer id,Difficulty newDifficulty)
    {
        Optional<Question> searchedQuestion=questionRepository.findById(id);
        if(searchedQuestion.isPresent())
        {
            Question ques=searchedQuestion.get();
            ques.setDifficulty(newDifficulty);
            return questionRepository.save(ques);
        }
        else
        {
            return null;
        }
    }

    public Question updateTitle(Integer id,String newTitle)
    {
        Optional<Question> searchedQuestion=questionRepository.findById(id);
        if(searchedQuestion.isPresent())
        {
            Question ques=searchedQuestion.get();
            ques.setTitle(newTitle);
            return questionRepository.save(ques);
        }
        else
        {
            return null;
        }
    }

    public Question deleteQuestion(Integer id,String title)
    {
        Question deletedQuestion=questionRepository.findByIdAndTitle(id,title);
        if(deletedQuestion==null)
        {
            return null;
        }
        else
        {
            questionRepository.delete(deletedQuestion);
            return deletedQuestion;
        }
    }

    public List<Question> getQuestionByDifficulty(Difficulty difficulty)
    {
        return questionRepository.findByDifficulty(difficulty);
    }

    public Question getQuestionByTitle(String title)
    {
        return questionRepository.findByTitle(title);
    }

    public List<Question> getAllQuestions()
    {
        return questionRepository.findAll();
    }

    public Question saveQuestion(Question question)
    {
        Question searchedQuestion=questionRepository.findByTitle(question.getTitle());
        if(searchedQuestion==null)
        {
            return questionRepository.save(question);
        }
        else
        {
            return null;
        }
    }
}
