package com.chatapp.backend.repository;
import com.chatapp.backend.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface Questionrepo extends JpaRepository<Question,Integer>{
	List<Question> findByQuizID(int i);
    
}