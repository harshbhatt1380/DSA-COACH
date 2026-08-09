package com.example.dsacoach.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.dsacoach.DTO.ResponseDTO.ProgressResponseDTO;
import com.example.dsacoach.DTO.ResponseDTO.ProgressResponseList;
import com.example.dsacoach.MyExceptions.UserNotFoundException;
import com.example.dsacoach.entity.Progress;
import com.example.dsacoach.entity.Question;
import com.example.dsacoach.entity.User;
import com.example.dsacoach.repository.ProgressRepository;
import com.example.dsacoach.repository.QuestionRepository;
import com.example.dsacoach.repository.UserRepository;

@Service
public class ProgressService 
{
    final UserRepository userRepository;
    final QuestionRepository questionRepository;
    final ProgressRepository progressRepository;

    public ProgressService(UserRepository userRepository,QuestionRepository questionRepository,ProgressRepository progressRepository)
    {
        this.userRepository=userRepository;
        this.questionRepository=questionRepository;
        this.progressRepository=progressRepository;
    }

    public ProgressResponseDTO addProgress(String username,String questionTitle,boolean solved)//Integer userId,
    {
        User user = userRepository.findByUsername(username);
        Question question = questionRepository.findByTitle(questionTitle);
        if(user==null)
        {
            return new ProgressResponseDTO(false,"user not found",null,null,false);
        }
        else if(question==null)
        {
            //Assuming we have a large database of problems
           return new ProgressResponseDTO(false,"question not found",null,null,false);
        }
        else
        {
            Progress progress=progressRepository.findByUserAndQuestion(user, question);
            if(progress==null)
            {
                progress = new Progress(user, question, solved);
                progressRepository.save(progress);
                return new ProgressResponseDTO(true,"New progress data entry created",progress.getUser().getUsername(),progress.getQuestion().getTitle(),progress.isSolved()); 
            }
            else
            {
                if(progress.isSolved()==solved)
                {
                    return new ProgressResponseDTO(true,"No updates in data entry of progress stat",progress.getUser().getUsername(),progress.getQuestion().getTitle(),progress.isSolved());
                }
                else
                {
                    progress.setSolved(solved);
                    progressRepository.save(progress);
                    return new ProgressResponseDTO(true,"Updated data entry of already existing progress stat",progress.getUser().getUsername(),progress.getQuestion().getTitle(),progress.isSolved());
                }
            }
        }
    } 
    public ProgressResponseDTO getQuestionProgress(String username,Integer qid)
    {
        User user=userRepository.findByUsername(username);
        if(user==null)
        {
            return new ProgressResponseDTO(false, "Invalid username, thus searching progress failed", null,null,false);
        }
        else
        {
            Optional<Question> ques=questionRepository.findById(qid);
            if(ques.isPresent())
            {
                Question question=ques.get();

                Progress progress=progressRepository.findByUserAndQuestion(user, question);

                if(progress==null)
                {
                    return new ProgressResponseDTO(true, "No progress record found for given user and question", user.getUsername(), question.getTitle(), false);
                }
                else
                {
                    return new ProgressResponseDTO(true, "Progress of user for provided question id fetched successfully ", user.getUsername(), question.getTitle(), progress.isSolved());
                }
            }
            else
            {
                return new ProgressResponseDTO(false, "Invalid question id , thus fetching progress failed", user.getUsername(), null, false);
            }
        }
    }
    public ArrayList<ProgressResponseList> getAllQuestionProgress(String username)
    {
        User user=userRepository.findByUsername(username);
        if(user==null)
        {
            throw new UserNotFoundException("User not found");
        }
        else
        {
            
            ArrayList<ProgressResponseList> responseList = new ArrayList<>();
            List<Progress> progressList=progressRepository.findByUser(user);
            for(Progress item : progressList)
            {
                responseList.add(new ProgressResponseList(item.getQuestion().getTitle(), item.isSolved()));
            }
            return responseList;
        }
    }
}
