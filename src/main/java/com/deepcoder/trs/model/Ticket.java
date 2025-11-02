package com.deepcoder.trs.model;

import com.deepcoder.trs.enums.TicketPriority;
import com.deepcoder.trs.enums.TicketStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
public class Ticket { //tickets
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; //findById

    @Column(nullable = false)
    private String subject; //findBySubject(subject)
    @Column(length = 1000)
    private String issue;

    private LocalDateTime createdAt = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private TicketPriority ticketPriority; //HIGH  | MEDIUM | LOW
    @Enumerated(EnumType.STRING)
    private TicketStatus ticketStatus; //findByTicketStatus(status)

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public TicketPriority getTicketPriority() {
        return ticketPriority;
    }

    public void setTicketPriority(TicketPriority ticketPriority) {
        this.ticketPriority = ticketPriority;
    }

    public TicketStatus getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(TicketStatus ticketStatus) {
        this.ticketStatus = ticketStatus;
    }
}
