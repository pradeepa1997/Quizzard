package com.chatapp.backend.repository;
import com.chatapp.backend.model.QuizCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizCategoryrepo extends JpaRepository<QuizCategory,Integer>{
    
}