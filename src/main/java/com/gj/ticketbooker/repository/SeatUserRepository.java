package com.gj.ticketbooker.repository;

import com.gj.ticketbooker.model.SeatUserAssociation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
This interface extends CrudRepository for movie table
 */
@Repository
@Transactional
public interface SeatUserRepository extends CrudRepository<SeatUserAssociation, Integer> {

    /**
     * This function queries the database to find if these seats are associated/booked to any user
     *
     * @param seatNumbers - the list of seats
     * @return List of SeatUserAssociation object not associated to any user
     */
    public List<SeatUserAssociation> findByUserIsNullAndSeatNumberIn(List<Integer> seatNumbers);
}
