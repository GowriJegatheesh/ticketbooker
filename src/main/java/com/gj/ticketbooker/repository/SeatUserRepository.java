package com.gj.ticketbooker.repository;

import com.gj.ticketbooker.model.SeatUserAssociation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface SeatUserRepository extends CrudRepository<SeatUserAssociation, Integer> {

    public List<SeatUserAssociation> findByUserIsNullAndSeatNumberIn(List<Integer> seatNumbers);
}
