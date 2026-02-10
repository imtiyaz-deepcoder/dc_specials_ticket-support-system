package com.deepcoder.trs.controller;

import com.deepcoder.trs.enums.TicketPriority;
import com.deepcoder.trs.model.Ticket;
import com.deepcoder.trs.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("/add")
    public Ticket insertTicket(@RequestBody Ticket ticket){
        return ticketService.add(ticket);
    }

    @GetMapping("/all")
    public List<Ticket> getAllTickets(@RequestParam("page") int page,
                                      @RequestParam("size") int size){
        List<Ticket> list =  ticketService.getAll(page,size);
        return list;
    }

    @GetMapping("/one/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id){
        try{
            Ticket ticket = ticketService.getById(id);
            return ResponseEntity.ok(ticket); //Ticket
        }
        catch(RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage()); //String
        }

    }

    @GetMapping("/status")
    public ResponseEntity<?> getByTicketStatus(@RequestParam("status") String status){
        try {
            List<Ticket> list = ticketService.getByTicketStatus(status);
            return ResponseEntity.ok(list);
        }
        catch(RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTicket(@PathVariable("id") int id){
       try {
           ticketService.deleteTicket(id);
           return ResponseEntity.ok("Ticket Deleted..");
       }
       catch(RuntimeException e){
           return ResponseEntity.badRequest().body(e.getMessage());
       }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTicket(@PathVariable("id") int id,
                             @RequestBody Ticket newTicket){

        try{
            Ticket ticket =  ticketService.updateTicket(id,newTicket);
            return ResponseEntity.ok(ticket);
        }
        catch(RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
