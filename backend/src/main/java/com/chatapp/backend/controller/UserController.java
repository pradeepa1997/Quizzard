package com.chatapp.backend.controller;

import java.util.List;
import com.chatapp.backend.model.User;
import com.chatapp.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value="/api/users")
public class UserController{
    
    @Autowired
    UserRepository usersrepo;
    
    @GetMapping(value = "/all")
    public List<User> getAllUsers() {
        return usersrepo.findAll();
    }

    @PostMapping(value = "/add")
    public String addNewUser(@RequestBody final User user) {
        System.out.println(user.getEmail());

        usersrepo.save(user);
        return "User added"; 
    }

    @PutMapping(value = "/update")
    public String updateUser(@RequestBody final User user) {
        String Upstatus = null ;
        if(user.getUserID() != null){
            usersrepo.save(user);
            Upstatus = "update successfull";
        }
        else{Upstatus = "ERROR : update not successfull";}
        System.out.print(user);
        return Upstatus; 
    }
    
}