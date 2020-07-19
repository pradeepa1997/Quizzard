package com.chatapp.backend.repository;

import com.chatapp.backend.model.Quiz;

import org.springframework.data.jpa.repository.JpaRepository;


public interface Quizrepo extends JpaRepository<Quiz,Integer>{
    
}