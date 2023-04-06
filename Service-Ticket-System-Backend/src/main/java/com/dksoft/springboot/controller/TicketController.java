package com.dksoft.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dksoft.springboot.model.Ticket;
import com.dksoft.springboot.reposiitory.TicketRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class TicketController {
	
	@Autowired
	private TicketRepository ticketRepository;

	// get all tickets
	@GetMapping("/tickets")
	public List<Ticket> getAllTickets(){
		return ticketRepository.findAll();
	}
	
	// create tickets
	@PostMapping("/tickets")
	public Ticket createTicket(@RequestBody Ticket ticket) {
		return ticketRepository.save(ticket);
	}

}
