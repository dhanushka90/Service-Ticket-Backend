package com.dksoft.springboot.reposiitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dksoft.springboot.model.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

}
