package com.gj.ticketbooker.repository;

import com.gj.ticketbooker.model.Theatre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface TheatreRepository extends CrudRepository<Theatre, Long> {
    public Theatre findByTheatreName(String name);

}
