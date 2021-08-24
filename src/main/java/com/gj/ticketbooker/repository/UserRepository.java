package com.gj.ticketbooker.repository;

import com.gj.ticketbooker.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/*
This interface extends CrudRepository for movie table
 */
@Repository
@Transactional
public interface UserRepository extends CrudRepository<User, Long> {

    /**
     * This function queries the database for records having the following username
     *
     * @param name - username
     * @return User object having the given username
     */
    public User findByName(String userName);

    /**
     * This function queries the database for records having the following username
     *
     * @param userName - username
     * @param password - password
     * @return User object having the given username
     */
    public User findByNameAndPassword(String userName, String password);
}
