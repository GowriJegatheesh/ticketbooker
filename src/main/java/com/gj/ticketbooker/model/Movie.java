package com.gj.ticketbooker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/*
This class represents the movie table
 */

@Data
@Entity
@Table(name = "movie")
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Long movieId;

    @Column(name = "movie_name")
    private String movieName;

    @OneToOne
    @JoinColumn(name = "theatre_fid", referencedColumnName = "theatre_id")
    private Theatre theatre;
}