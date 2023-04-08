package com.dksoft.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dksoft.springboot.exception.ResourceNotFoundException;
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
	
	// get ticket by id
	@GetMapping("/tickets/{id}")
	public ResponseEntity<Ticket> getTicketById(@PathVariable Long id) {
		
		Ticket ticket = ticketRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("There is no ticket exist with id:" +id));
		
		return ResponseEntity.ok(ticket);
	}
	
	// update ticket
	@PutMapping("/tickets/{id}")
	public ResponseEntity<Ticket> updateTicket(@PathVariable Long id, @RequestBody Ticket ticketDetails){
		
		Ticket ticket = ticketRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("There is no ticket exist with id:" +id));
		
		ticket.setTitle(ticketDetails.getTitle());
		ticket.setDescription(ticketDetails.getDescription());
		ticket.setFirstName(ticketDetails.getFirstName());
		ticket.setLastName(ticketDetails.getLastName());
		ticket.setEmail(ticketDetails.getEmail());
		ticket.setMobile(ticketDetails.getMobile());
		ticket.setResolution(ticketDetails.getResolution());
		
		Ticket updatedTicket = ticketRepository.save(ticket);
		return ResponseEntity.ok(updatedTicket);
	}

	// delete ticket
	@DeleteMapping("/tickets/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteTicket(@PathVariable Long id) {
		
		Ticket ticket = ticketRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("There is no ticket exist with id:" +id));
		
		ticketRepository.delete(ticket);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

}
