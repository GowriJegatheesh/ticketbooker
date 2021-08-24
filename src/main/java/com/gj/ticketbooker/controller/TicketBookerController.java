package com.gj.ticketbooker.controller;

import com.gj.ticketbooker.model.TicketRequest;
import com.gj.ticketbooker.model.TicketResponse;
import com.gj.ticketbooker.service.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

/*
This class contains the method for the exposed API
 */
@RestController
@RequestMapping("/api/v1")
public class TicketBookerController {

    @Autowired
    IBookingService bookingService;

    @PostMapping("/ticket")
    public TicketResponse bookTicket(@RequestBody TicketRequest ticketRequest) throws ExecutionException, InterruptedException {
        return bookingService.bookTicket(ticketRequest);
    }


}
