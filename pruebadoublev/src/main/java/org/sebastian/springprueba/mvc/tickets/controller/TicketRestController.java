package org.sebastian.springprueba.mvc.tickets.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.sebastian.springprueba.mvc.tickets.dto.request.TicketDtoRequest;
import org.sebastian.springprueba.mvc.tickets.dto.response.ResponseDto;
import org.sebastian.springprueba.mvc.tickets.response.TicketResponseRest;
import org.sebastian.springprueba.mvc.tickets.service.ITicketServices;
import org.sebastian.springprueba.mvc.tickets.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class TicketRestController {


    @Autowired
    private ITicketServices service;

    @GetMapping("/tickets")
    public ResponseEntity<TicketResponseRest> getAllTicket() {
        ResponseEntity<TicketResponseRest> response = service.getAllByTicket();
        return response;
    }

    @GetMapping("/tickets/{id}")
    public ResponseEntity<TicketResponseRest> getTicketId(@PathVariable Long id) {
        ResponseEntity<TicketResponseRest> response = service.getTicketId(id);
        return response;
    }

    @PostMapping("/tickets")
    public ResponseEntity<ResponseDto> saveTicket(@RequestBody TicketDtoRequest ticket){
        ResponseDto server = service.saveTicket(ticket);
        return new ResponseEntity<>(server, server.getStatus());
    }

    @PutMapping("/tickets/{id}")
    public ResponseEntity<TicketResponseRest> updateTicket(@RequestBody Ticket ticket, @PathVariable Long id){

        ResponseEntity<TicketResponseRest> response = service.updateTicket(ticket, id);
        return response;
    }

    @DeleteMapping("/tickets/{id}")
    public ResponseEntity<TicketResponseRest> deleteTicket(@PathVariable Long id){
        ResponseEntity<TicketResponseRest> response = service.deleteTicket(id);
        return response;
    }
    @GetMapping("/tickets/page/{page}/size/{size}")
    public Page<Ticket> listarClientesPaginado(@PathVariable Integer page, @PathVariable Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return service.findAllPageable(pageable);
    }
}
