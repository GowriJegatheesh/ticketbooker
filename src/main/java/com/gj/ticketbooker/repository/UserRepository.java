package com.gj.ticketbooker.repository;

import com.gj.ticketbooker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserRepository extends CrudRepository<User, Long> {

    public User findByName(String userName);

    public User findByNameAndPassword(String userName, String password);
}
