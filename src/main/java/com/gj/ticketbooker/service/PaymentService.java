package com.gj.ticketbooker.service;

import com.gj.ticketbooker.model.TicketRequest;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    /**
     * This function is used to call the third party tool for payment
     *
     * @param ticketRequest - the object describing the user's preference of movie, theatre and seats
     * @return true if the payment succeeds
     */
    public boolean makePayment(TicketRequest ticketRequest) {
        // the thrid party tool can be called from here
        return true;
    }
}
