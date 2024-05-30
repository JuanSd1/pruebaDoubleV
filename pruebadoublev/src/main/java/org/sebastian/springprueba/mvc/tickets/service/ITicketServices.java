package org.sebastian.springprueba.mvc.tickets.service;


import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.sebastian.springprueba.mvc.tickets.dto.request.TicketDtoRequest;
import org.sebastian.springprueba.mvc.tickets.dto.response.ResponseDto;
import org.sebastian.springprueba.mvc.tickets.response.TicketResponseRest;
import org.sebastian.springprueba.mvc.tickets.entity.Ticket;
import org.springframework.http.ResponseEntity;

public interface ITicketServices {

    ResponseEntity<TicketResponseRest> getAllByTicket();
    ResponseEntity<TicketResponseRest> getTicketId(Long id);
    ResponseEntity<TicketResponseRest> deleteTicket(Long id);
    ResponseEntity<TicketResponseRest> updateTicket(Ticket ticket, Long id);
    ResponseDto saveTicket(TicketDtoRequest ticket);
    Page<Ticket> findAllPageable(Pageable pageable);

}
