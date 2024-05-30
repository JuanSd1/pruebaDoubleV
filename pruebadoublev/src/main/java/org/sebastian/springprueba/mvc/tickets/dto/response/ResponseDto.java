package org.sebastian.springprueba.mvc.tickets.dto.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ResponseDto {

    private HttpStatus status;
    private String mensaje;
    private Object object;

}
