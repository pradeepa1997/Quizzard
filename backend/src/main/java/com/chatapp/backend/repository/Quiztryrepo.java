package com.chatapp.backend.repository;

import java.util.List;

import com.chatapp.backend.model.Quiztry;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Quiztryrepo extends JpaRepository<Quiztry,Integer> {
       
}