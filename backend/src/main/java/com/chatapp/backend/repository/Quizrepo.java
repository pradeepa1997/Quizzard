package com.chatapp.backend.repository;

import java.util.List;

import com.chatapp.backend.model.Quiz;

import org.springframework.data.jpa.repository.JpaRepository;


public interface Quizrepo extends JpaRepository<Quiz,Integer>{
    List<Quiz> findByQuizName(String quizName);    
}