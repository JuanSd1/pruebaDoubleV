package org.sebastian.springprueba.mvc.tickets.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class TicketDtoRequest {

    private String usuario;
    private String status;

}
