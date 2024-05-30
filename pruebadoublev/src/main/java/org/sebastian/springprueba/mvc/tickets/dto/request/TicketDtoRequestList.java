package org.sebastian.springprueba.mvc.tickets.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class TicketDtoRequestList {

    List<TicketDtoRequest> ticketDtoRequests;

}
