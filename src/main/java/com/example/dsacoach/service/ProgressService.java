package com.example.dsacoach.service;

import org.springframework.stereotype.Service;

import com.example.dsacoach.DTO.ResponseDTO.ProgressResponseDTO;
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
            return new ProgressResponseDTO(false,"user not found",null);
        }
        else if(question==null)
        {
            //Assuming we have a large database of problems
           return new ProgressResponseDTO(false,"question not found",null);
        }
        else
        {
            Progress progress=progressRepository.findByUserAndQuestion(user, question);
            if(progress==null)
            {
                progress = new Progress(user, question, solved);
                progressRepository.save(progress);
                return new ProgressResponseDTO(true,"New progress data entry created",progress); 
            }
            else
            {
                if(progress.isSolved()==solved)
                {
                    return new ProgressResponseDTO(true,"No updates in data entry of progress stat",progress);
                }
                else
                {
                    progress.setSolved(solved);
                    progressRepository.save(progress);
                    return new ProgressResponseDTO(true,"Updated data entry of already existing progress stat",progress);
                }
            }
        }
    } 
}
