package org.sebastian.springprueba.mvc.tickets.service;


import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.sebastian.springprueba.mvc.tickets.dto.request.TicketDtoRequest;
import org.sebastian.springprueba.mvc.tickets.dto.response.ResponseDto;
import org.sebastian.springprueba.mvc.tickets.response.TicketResponseRest;
import org.sebastian.springprueba.mvc.tickets.entity.Ticket;
import org.springframework.http.ResponseEntity;

public interface ITicketServices {

    public ResponseEntity<TicketResponseRest> getAllByTicket();
    public ResponseEntity<TicketResponseRest> getTicketId(Long id);
    public ResponseEntity<TicketResponseRest> deleteTicket(Long id);
    public ResponseEntity<TicketResponseRest> updateTicket(Ticket ticket, Long id);
    public ResponseDto saveTicket(TicketDtoRequest ticket);
    public Page<Ticket> findAllPageable(Pageable pageable);

}
