package com.test.tyro.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.test.tyro.model.User;
import com.test.tyro.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UserController {
    
    @Autowired UserRepository userRepo;

    @GetMapping("/user")
    public List<User> listAll(@RequestParam String param) {
        return userRepo.findAll();
    }
    
}
