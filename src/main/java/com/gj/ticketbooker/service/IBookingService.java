package com.gj.ticketbooker.service;

import com.gj.ticketbooker.model.TicketRequest;
import com.gj.ticketbooker.model.TicketResponse;

import java.util.concurrent.ExecutionException;

/*
This interface contains declaration of methods related to BookingService
 */
public interface IBookingService {

    /**
     * This function book the ticket if it meets all the required criteria
     *
     * @param ticketRequest - the object describing the user's preference of movie, theatre and seats
     * @return TicketResponse object to tell the status of the ticket
     */
    public TicketResponse bookTicket(TicketRequest ticketRequest) throws ExecutionException, InterruptedException;
}
