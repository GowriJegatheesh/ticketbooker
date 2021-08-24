package com.gj.ticketbooker.service;

import com.gj.ticketbooker.model.Movie;

/*
This interface contains declaration of methods related to MovieService
 */
public interface IMovieService {

    /**
     * This function is used to find the records that match the name and the theatre name
     *
     * @param name        - the name of the movie user wishes to go
     * @param theatreName - the name of the theatre user wishes to go
     * @return Movie object containing the name and running in the theatre with the theatreName
     */

    public Movie findByMovieNameAndTheatreName(String name, String theatreName);
}
