package com.gj.ticketbooker.repository;

import com.gj.ticketbooker.model.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface MovieRepository extends CrudRepository<Movie, Long> {
    /**
     * abcd
     * @param name - Movie Name
     * @param theatreName - theatre Name
     * @return Movie object
     */
    public Movie findByMovieNameAndTheatreTheatreName(String name, String theatreName);
}
