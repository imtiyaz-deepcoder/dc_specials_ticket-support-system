package com.deepcoder.trs.repository;

import com.deepcoder.trs.enums.TicketStatus;
import com.deepcoder.trs.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Integer> {
    /*
    * After this, i have all methods of JPARepository
    * */

    List<Ticket> findByTicketStatus(TicketStatus status);
}
