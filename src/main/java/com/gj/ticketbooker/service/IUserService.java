package com.gj.ticketbooker.service;

import com.gj.ticketbooker.model.User;

/*
This interface contains declaration of methods related to UserService
 */
public interface IUserService {
    /**
     * This function is used to find the user with the name
     *
     * @param name - name of the user
     * @return User object having the name
     */
    public User findByUser(String name);

    /**
     * This function is used to find the user with the name and the password
     *
     * @param name     - name of the user
     * @param password - password of the user
     * @return User object having the name and the password
     */
    public User findByUserAndPassword(String name, String password);
}
