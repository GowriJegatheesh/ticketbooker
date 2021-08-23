package com.gj.ticketbooker.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "seat_user_association")
@Data
public class SeatUserAssociation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_number")
    private Integer seatNumber;

    @OneToOne
    @JoinColumn(name="user_fid", referencedColumnName = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name="movie_fid", referencedColumnName = "movie_id")
    private Movie movie;



}
