package com.deepcoder.trs.service;

import com.deepcoder.trs.enums.TicketPriority;
import com.deepcoder.trs.enums.TicketStatus;
import com.deepcoder.trs.model.Ticket;
import com.deepcoder.trs.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public Ticket add(Ticket ticket) {
        ticket.setTicketStatus(TicketStatus.OPEN);
        return ticketRepository.save(ticket);
    }

    public List<Ticket> getAll() {
        List<Ticket> list = ticketRepository.findAll();
        return list;
    }

    public List<Ticket> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Ticket> list = ticketRepository.findAll(pageable).getContent();
        return list;
    }


    public Ticket getById(int id) {
        Optional<Ticket> optional = ticketRepository.findById(id);
        if(optional.isEmpty()){
            throw new RuntimeException("Invalid Ticket Id");
        }
        Ticket ticket = optional.get();
        return ticket;
    }

    public List<Ticket> getByTicketStatus(String status) {
       TicketStatus ticketStatus =  TicketStatus.valueOf(status);
       return ticketRepository.findByTicketStatus(ticketStatus);
    }

    public void deleteTicket(int id) {
        Optional<Ticket> optional = ticketRepository.findById(id);
        if(optional.isEmpty()){
            throw new RuntimeException("Invalid Ticket Id");
        }
        ticketRepository.deleteById(id);
    }

    public Ticket updateTicket(int id, Ticket newTicket) {

        Optional<Ticket> optional = ticketRepository.findById(id);
        if(optional.isEmpty())
            throw new RuntimeException("Invalid Ticket ID");
        Ticket existingTicket = optional.get();

        existingTicket.setSubject(newTicket.getSubject());
        existingTicket.setIssue(newTicket.getIssue());
        existingTicket.setTicketPriority(newTicket.getTicketPriority());

        return ticketRepository.save(existingTicket);
    }
}
