package com.example.dsacoach.service;

 
import org.springframework.stereotype.Service;

import com.example.dsacoach.DTO.ResponseDTO.QuestionResponseDTO;
import com.example.dsacoach.DTO.ResponseDTO.QuestionResponseList;
import com.example.dsacoach.MyExceptions.QuestionAlreadyExistsException;
import com.example.dsacoach.MyExceptions.QuestionNotFoundException;
import com.example.dsacoach.MyExceptions.QuestionTitleAlreadyTakenException;
import com.example.dsacoach.entity.Question;
import com.example.dsacoach.enumFolder.Difficulty;
import com.example.dsacoach.repository.QuestionRepository;

import java.util.ArrayList;
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

    public QuestionResponseDTO findByTitle(String title)
    {
        Question ques = questionRepository.findByTitle(title);
        if(ques==null)
        {
            throw new QuestionNotFoundException("No question with given title found");
        }
        else
        {
            return new QuestionResponseDTO(true, "Question found via title search", ques.getTitle(), ques.getDifficulty());
        }
    }

    public QuestionResponseDTO findById(Integer id)
    {
        Optional<Question> searchedQuestion=questionRepository.findById(id);
        if(searchedQuestion.isPresent())
        {
            Question ques=searchedQuestion.get();
            return new QuestionResponseDTO(true, "Question found by Id", ques.getTitle(), ques.getDifficulty());
        }
        else
        {
            throw new QuestionNotFoundException("No question found with the given id : "+id);
        }
    }

    public QuestionResponseDTO updateDifficulty(Integer id,Difficulty newDifficulty)
    {
        Optional<Question> searchedQuestion=questionRepository.findById(id);
        if(searchedQuestion.isPresent())
        {
            Question ques=searchedQuestion.get();
            ques.setDifficulty(newDifficulty);
            questionRepository.save(ques);
            return new QuestionResponseDTO(true, "Difficulty of question updated", ques.getTitle(), ques.getDifficulty());
        }
        else
        {
            throw new QuestionNotFoundException("No question found with provided id in the database thus difficulty cannot be changed");
        }
    }

    public QuestionResponseDTO updateTitle(Integer id,String newTitle)
    {
        Optional<Question> searchedQuestion=questionRepository.findById(id);
        if(searchedQuestion.isPresent())
        {
            Question ques=searchedQuestion.get();
            Question checkQuestion=questionRepository.findByTitle(newTitle);
            if(checkQuestion!=null)
            {
                throw new QuestionTitleAlreadyTakenException(newTitle+" is already assigned as title to some other question in the database,thus cannot update question name.");
            }
            else
            {
                ques.setTitle(newTitle);
                questionRepository.save(ques);
                return new QuestionResponseDTO(true, "Title updated for question", ques.getTitle(), ques.getDifficulty());
            }
        }
        else
        {
            throw new QuestionNotFoundException("No question found with provided id in the database thus title cannot be changed");
        }
    }

    public QuestionResponseDTO deleteQuestion(Integer id,String title)
    {
        Question deletedQuestion=questionRepository.findByIdAndTitle(id,title);
        if(deletedQuestion==null)
        {
            throw new QuestionNotFoundException("No question found with provided id and title in the database thus deletion of question failed");
        }
        else
        {
            questionRepository.delete(deletedQuestion);
            return new QuestionResponseDTO(true, "Deletion of question successful", deletedQuestion.getTitle(), deletedQuestion.getDifficulty());
        }
    }

    public ArrayList<QuestionResponseList> getQuestionByDifficulty(Difficulty difficulty)
    {
        ArrayList<QuestionResponseList> responseList=new ArrayList<>();
        List<Question> result=questionRepository.findByDifficulty(difficulty);
        for(Question item : result)
        {
            responseList.add(new QuestionResponseList(item.getTitle(), item.getDifficulty()));
        }
        return responseList;
    }

    public ArrayList<QuestionResponseList> getAllQuestions()
    {
        List<Question> result= questionRepository.findAll();
        ArrayList<QuestionResponseList> responseList=new ArrayList<>();

        for(Question item : result)
        {
            responseList.add(new QuestionResponseList(item.getTitle(), item.getDifficulty()));
        }
        return responseList;
    }

    public QuestionResponseDTO saveQuestion(Question question)
    {
        Question searchedQuestion=questionRepository.findByTitle(question.getTitle());
        if(searchedQuestion==null)
        {
            searchedQuestion=questionRepository.save(question);
            return new QuestionResponseDTO(true,"Question saved successfully" ,searchedQuestion.getTitle() ,searchedQuestion.getDifficulty());
        }
        else
        {
            throw new QuestionAlreadyExistsException("Question with provided title already exists thus could not create question entity");
        }
    }
}
