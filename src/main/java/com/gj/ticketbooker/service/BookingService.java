package com.gj.ticketbooker.service;

import com.gj.ticketbooker.model.SeatUserAssociation;
import com.gj.ticketbooker.model.TicketRequest;
import com.gj.ticketbooker.model.TicketResponse;
import com.gj.ticketbooker.model.User;
import com.gj.ticketbooker.repository.SeatUserRepository;
import com.gj.ticketbooker.runner.AsyncRunner;
import com.gj.ticketbooker.runner.ThreadAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

/*
This service class defines the business logic
 */
@Service
public class BookingService implements IBookingService {

    @Autowired
    SeatUserRepository seatUserRepository;

    @Autowired
    AsyncRunner asyncRunner;

    @Autowired
    IUserService userService;

    @Autowired
    PaymentService paymentService;

    @Autowired
    IMovieService movieService;

    /**
     * This function book the ticket if it meets all the required criteria
     *
     * @param ticketRequest - the object describing the user's preference of movie, theatre and seats
     * @return TicketResponse object to tell the status of the ticket
     */
    @Override
    public TicketResponse bookTicket(TicketRequest ticketRequest) throws ExecutionException, InterruptedException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();

        //Check if the movie is running in the theatre requested by the user
        if (movieService.findByMovieNameAndTheatreName(ticketRequest.getMovieName(), ticketRequest.getTheatreName()) == null) {
            return new TicketResponse("Movie/Theatre not found");
        }

        //Check if the seat is booked by any other user
        List<SeatUserAssociation> filledSeats = seatUserRepository.findByUserIsNullAndSeatNumberIn(ticketRequest.getSeats());

        if (filledSeats.size() != ticketRequest.getSeats().size()) {
            return new TicketResponse("The seats are already booked");
        }

        //Add this user in the input list of asyncRunner
        ThreadAdapter.ticketRequests.put(auth.getName(), ticketRequest.getSeats());

        //Wait for the asyncRunner to process the requests from multiple users
        synchronized (asyncRunner) {
            asyncRunner.wait();
        }

        //Check if user is available in the result after asyncRunner processed
        if (!ThreadAdapter.ticketResults.containsKey(userName)) {
            return new TicketResponse("Can not book tickets");
        }

        //Remove this user from the input list of asyncRunner
        ThreadAdapter.ticketResults.remove(userName);

        //Check if payment is successfull
        if (!paymentService.makePayment(ticketRequest)) {
            return new TicketResponse("Payment Failed");
        }

        //Associate the user to all the seats the user wished for
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
