package com.example.dsacoach.service;

import org.springframework.stereotype.Service;

import com.example.dsacoach.entity.Progress;
import com.example.dsacoach.entity.Question;
import com.example.dsacoach.entity.User;
import com.example.dsacoach.repository.ProgressRepository;
import com.example.dsacoach.repository.QuestionRepository;
import com.example.dsacoach.repository.UserRepository;
import com.example.dsacoach.resultObject.ProgressOperationResult;

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

    public ProgressOperationResult addProgress(String username,String questionTitle,boolean solved)//Integer userId,
    {
        User user = userRepository.findByUsername(username);
        Question question = questionRepository.findByTitle(questionTitle);
        if(user==null)
        {
            return new ProgressOperationResult(false,"user not found",null);
        }
        else if(question==null)
        {
            //Assuming we have a large database of problems
           return new ProgressOperationResult(false,"question not found",null);
        }
        else
        {
            Progress progress=progressRepository.findByUserAndQuestion(user, question);
            if(progress==null)
            {
                progress = new Progress(user, question, solved);
                progressRepository.save(progress);
                return new ProgressOperationResult(true,"New progress data entry created",progress); 
            }
            else
            {
                if(progress.isSolved()==solved)
                {
                    return new ProgressOperationResult(true,"No updates in data entry of progress stat",progress);
                }
                else
                {
                    progress.setSolved(solved);
                    progressRepository.save(progress);
                    return new ProgressOperationResult(true,"Updated data entry of already existing progress stat",progress);
                }
            }
        }
    } 
}
