package com.gj.ticketbooker.repository;

import com.gj.ticketbooker.model.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/*
This interface extends CrudRepository for movie table
 */
@Repository
@Transactional
public interface MovieRepository extends CrudRepository<Movie, Long> {
    /**
     * This function queries the database for records having the following movie name and theatre name
     *
     * @param name        - Movie Name
     * @param theatreName - theatre Name
     * @return Movie record containing the movie name and referring the theatre having the theatreName
     */
    public Movie findByMovieNameAndTheatreTheatreName(String name, String theatreName);
}
