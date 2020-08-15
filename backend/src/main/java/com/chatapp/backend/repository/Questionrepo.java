package com.chatapp.backend.repository;

import java.util.List;

import com.chatapp.backend.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;


public interface Questionrepo extends JpaRepository<Question,Integer>{
    List<Question> findByQuizID(Integer quizID);
}