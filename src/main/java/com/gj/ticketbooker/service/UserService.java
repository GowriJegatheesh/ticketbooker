package com.gj.ticketbooker.service;

import com.gj.ticketbooker.model.User;
import com.gj.ticketbooker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
This service class defines the method in IUserService
 */
@Service
public class UserService implements IUserService {
    @Autowired
    UserRepository userRepository;

    /**
     * This function is used to find the user with the name
     *
     * @param name - name of the user
     * @return User object having the name
     */
    public User findByUser(String name) {
        return userRepository.findByName(name);
    }

    /**
     * This function is used to find the user with the name and the password
     *
     * @param name     - name of the user
     * @param password - password of the user
     * @return User object having the name and the password
     */
    public User findByUserAndPassword(String name, String password) {
        return userRepository.findByNameAndPassword(name, password);
    }
}
