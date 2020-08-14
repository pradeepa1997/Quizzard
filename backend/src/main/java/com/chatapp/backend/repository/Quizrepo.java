package com.chatapp.backend.repository;
import java.util.List;
import com.chatapp.backend.model.Quiz;
import com.chatapp.backend.model.Quiztry;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Quizrepo extends JpaRepository<Quiz,Integer>{
    List<Quiz> findByQuizName(String quizName);
    List<Quiz> findByCreatorID(Integer creatorID);

	List<Quiz> findBycreatorID(int i);
  
}