package com.gj.ticketbooker.service;

import com.gj.ticketbooker.model.Theatre;
import com.gj.ticketbooker.repository.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TheatreService {

    @Autowired
    TheatreRepository theatreRepository;

    public Theatre findByTheatreName(String name){
        return theatreRepository.findByTheatreName(name);
    }
}
