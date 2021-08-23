package com.gj.ticketbooker.service;

import com.gj.ticketbooker.model.User;
import com.gj.ticketbooker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User findByUser(String name){
        return userRepository.findByName(name);
    }

    public User findByUserAndPassword(String name, String password){
        return userRepository.findByNameAndPassword(name, password);
    }
}
