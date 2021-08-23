package com.gj.ticketbooker.service;

import com.gj.ticketbooker.model.TicketRequest;
import com.gj.ticketbooker.model.TicketResponse;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

public interface IBookingService {

    public TicketResponse bookTicket(TicketRequest ticketRequest) throws ExecutionException, InterruptedException;
}
