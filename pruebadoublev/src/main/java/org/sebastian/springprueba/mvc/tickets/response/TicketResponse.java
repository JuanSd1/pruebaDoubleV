package org.sebastian.springprueba.mvc.tickets.response;

import lombok.Data;
import org.sebastian.springprueba.mvc.tickets.entity.Ticket;

import java.util.List;

@Data
public class TicketResponse {

    private List<Ticket> ticket;

}
