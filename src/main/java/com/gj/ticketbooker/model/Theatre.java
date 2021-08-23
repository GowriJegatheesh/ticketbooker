package com.gj.ticketbooker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "theatre")
@AllArgsConstructor
@NoArgsConstructor
public class Theatre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "theatre_id")
    private Long theatreId;

    @Column(name = "theatre_name")
    private String theatreName;
}
