package com.gj.ticketbooker.service;

import com.gj.ticketbooker.model.SeatUserAssociation;
import com.gj.ticketbooker.model.TicketRequest;
import com.gj.ticketbooker.model.TicketResponse;
import com.gj.ticketbooker.model.User;
import com.gj.ticketbooker.repository.SeatUserRepository;
import com.gj.ticketbooker.runner.AsyncRunner;
import com.gj.ticketbooker.runner.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class BookingService implements IBookingService {

    @Autowired
    SeatUserRepository seatUserRepository;

    @Autowired
    AsyncRunner asyncRunner;

    @Autowired
    UserService userService;

    @Autowired
    PaymentService paymentService;

    @Autowired
    MovieService movieService;

    @Autowired
    TheatreService theatreService;

    @Override
    public TicketResponse bookTicket(TicketRequest ticketRequest) throws ExecutionException, InterruptedException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();

        if(movieService.findByMovieNameAndTheatreName(ticketRequest.getMovieName(), ticketRequest.getTheatreName()) == null){
            return new TicketResponse("Movie/Theatre not found");
        }

        List<SeatUserAssociation> filledSeats = seatUserRepository.findByUserIsNullAndSeatNumberIn(ticketRequest.getSeats());

        if (filledSeats.size() != ticketRequest.getSeats().size()) {
            return new TicketResponse("The seats are already booked");
        }

        Value.ticketRequests.put(auth.getName(), ticketRequest.getSeats());

        synchronized (asyncRunner) {
            asyncRunner.wait();
        }
        if (!Value.ticketResults.containsKey(userName)) {
            return new TicketResponse("Can not book tickets");
        }
        Value.ticketResults.remove(userName);

        if (!paymentService.makePayment(ticketRequest)) {
            return new TicketResponse("Payment Failed");
        }

        User user = userService.findByUser(userName);
        SeatUserAssociation seatUserAssociation = new SeatUserAssociation();
        seatUserAssociation.setUser(user);
        for (int seatNum : ticketRequest.getSeats()) {
            seatUserAssociation.setSeatNumber(seatNum);
            seatUserRepository.save(seatUserAssociation);
        }

        return new TicketResponse("Success");
    }


}
