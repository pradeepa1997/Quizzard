package com.chatapp.backend.controller;

import java.util.List;

import com.chatapp.backend.model.User;
import com.chatapp.backend.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value="/api/users")
public class UserController{
    @Autowired
    UserRepository usersrepo;
    @GetMapping(value = "/all")
    public List<User> getall(){
        return usersrepo.findAll();
    }

    @PostMapping(value = "/add")
    public List<User> add(@RequestBody final User user){
        System.out.println(user);
        usersrepo.save(user);
        return usersrepo.findAll();
    }
    
    
}