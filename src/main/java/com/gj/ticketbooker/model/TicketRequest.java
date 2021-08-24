package com.gj.ticketbooker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/*
This class represents the TicketRequest model
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketRequest {
    private String movieName;
    private String theatreName;
    private List<Integer> seats;

}
