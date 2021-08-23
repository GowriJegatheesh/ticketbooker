package com.gj.ticketbooker.service;

import com.gj.ticketbooker.model.Movie;
import com.gj.ticketbooker.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public Movie findByMovieNameAndTheatreName(String name, String theatreName){
        return movieRepository.findByMovieNameAndTheatreTheatreName(name, theatreName);
    }
}
