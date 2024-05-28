package org.sebastian.springprueba.mvc.tickets.service.impl;

import org.sebastian.springprueba.mvc.tickets.service.ITicketServices;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import org.modelmapper.ModelMapper;
import org.sebastian.springprueba.mvc.tickets.constante.Constante;
import org.sebastian.springprueba.mvc.tickets.dto.request.TicketDtoRequest;
import org.sebastian.springprueba.mvc.tickets.dto.response.ResponseDto;
import org.sebastian.springprueba.mvc.tickets.repository.TicketRepository;
import org.sebastian.springprueba.mvc.tickets.response.TicketResponseRest;
import org.sebastian.springprueba.mvc.tickets.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class TicketServiceImpl implements ITicketServices {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public ResponseEntity<TicketResponseRest> getAllByTicket() {
        TicketResponseRest response = new TicketResponseRest();

        try{
            List<Ticket> ticket = (List<Ticket>) ticketRepository.findAll();
            response.getTicketResponse().setTicket(ticket);
            response.setMetadata("Respuesta OK", "00", "Respuesta exitosa");
        }catch (Exception e) {
            response.setMetadata("Respuesta nok", "-1", "Error al consultar");
            e.getStackTrace();
            return new ResponseEntity<TicketResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
         return new ResponseEntity<TicketResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TicketResponseRest> getTicketId(Long id) {
        TicketResponseRest response = new TicketResponseRest();
        List<Ticket> list = new ArrayList<>();

        try{
            Optional<Ticket> ticket = ticketRepository.findById(id);
            if(ticket.isPresent()){
                list.add(ticket.get());
                response.getTicketResponse().setTicket(list);
                response.setMetadata("Respuesta ok", "00", "Ticket encontrada");
            }else {
                response.setMetadata("Respuesta nok", "-1", "Ticket no encontrada");
                return new ResponseEntity<TicketResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e) {
            response.setMetadata("Respuesta nok", "-1", "Error al consultar por id");
            e.getStackTrace();
            return new ResponseEntity<TicketResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<TicketResponseRest>(response,HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<TicketResponseRest> deleteTicket(Long id) {
        TicketResponseRest response = new TicketResponseRest();
        try{
            ticketRepository.deleteById(id);
            response.setMetadata("Respuesta ok", "00", "Registro eliminado");
        }catch (Exception e) {
            response.setMetadata("Respuesta nok", "-1", "Error al grabar Ticket");
            e.getStackTrace();
            return new ResponseEntity<TicketResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<TicketResponseRest>(response,HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<TicketResponseRest> updateTicket(Ticket ticket, Long id) {
        TicketResponseRest response = new TicketResponseRest();
        List<Ticket> list = new ArrayList<>();

        Optional<Ticket> ticketSearch = ticketRepository.findById(id);
        try {
            if (ticketSearch.isPresent()) {
                ticketSearch.get().setUsuario(ticket.getUsuario());
                ticketSearch.get().setFechaActualizacion(LocalDateTime.now());
                ticketSearch.get().setStatus(ticket.getStatus());
                Ticket categoryToUpdate = ticketRepository.save(ticketSearch.get());

                if (categoryToUpdate != null) {
                    list.add(categoryToUpdate);
                    response.getTicketResponse().setTicket(list);
                    response.setMetadata("Respuesta ok", "00", "Ticket actualizada");
                }else {
                    response.setMetadata("Respuesta nok", "-1", "Ticket no actualizada");
                    return new ResponseEntity<TicketResponseRest>(response, HttpStatus.BAD_REQUEST);
                }
            }else {
                response.setMetadata("Respuesta nok", "-1", "Ticket no encontrada");
                return new ResponseEntity<TicketResponseRest>(response, HttpStatus.NOT_FOUND);
            }

        }catch (Exception e){
            response.setMetadata("Respuesta nok", "-1", "Ticket no encontrada");
            e.getStackTrace();
            return new ResponseEntity<TicketResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<TicketResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseDto saveTicket(TicketDtoRequest ticketDtoRequest) {
        ResponseDto response = new ResponseDto();

        try{

            if (!Constante.ESTADO_ABIERTO.equals(ticketDtoRequest.getStatus()) && !Constante.ESTADO_CERRADO.equals(ticketDtoRequest.getStatus())) {
                response.setObject(null);
                response.setMensaje("El status debe ser 'abierto' o 'cerrado'");
                response.setStatus(HttpStatus.BAD_REQUEST);
                return response;
            }

            Ticket ticket = modelMapper.map(ticketDtoRequest, Ticket.class);
            ticket.setFechaCreacion(LocalDateTime.now());
            ticket.setFechaActualizacion(LocalDateTime.now());
            Ticket categoriSave = ticketRepository.save(ticket);

            if (Objects.nonNull(categoriSave)){
                response.setObject(categoriSave);
                response.setMensaje(Constante.REGISTRO_CREADO);
                response.setStatus(HttpStatus.OK);
            }else {
                response.setObject(categoriSave);
                response.setMensaje(Constante.REGISTRO_NO_CREADO);
                response.setStatus(HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e) {
            response.setObject(e.getMessage());
            response.setMensaje(Constante.ERROR_SERVER);
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Ticket> findAllPageable(Pageable pageable){
        return ticketRepository.findAll(pageable);
    }
}




