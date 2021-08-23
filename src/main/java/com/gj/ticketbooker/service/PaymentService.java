package com.gj.ticketbooker.service;

import com.gj.ticketbooker.model.TicketRequest;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    public boolean makePayment(TicketRequest ticketRequest){
        // the thrid party tool can be called from here
        return true;
    }
}
