package com.campusConnect.CampusConnect.infrastructure.services.fuertes;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.campusConnect.CampusConnect.api.dto.request.fuertes.EventoAgendaReq;
import com.campusConnect.CampusConnect.api.dto.response.fuertes.EventoAgendaResp;
import com.campusConnect.CampusConnect.domain.entities.fuertes.Evento;
import com.campusConnect.CampusConnect.domain.entities.intermedias.Agenda;
import com.campusConnect.CampusConnect.domain.repositories.fuertes.EventoRepository;
import com.campusConnect.CampusConnect.domain.repositories.intermedias.AgendaRepository;
import com.campusConnect.CampusConnect.infrastructure.abstract_services.fuertes.IEventoAgendaService;
import com.campusConnect.CampusConnect.util.enums.SortType;
import com.campusConnect.CampusConnect.util.exceptions.BadRequestException;
import com.campusConnect.CampusConnect.util.messages.ErrorMessages;

import lombok.AllArgsConstructor;
import lombok.Data;

@Service
@Data
@AllArgsConstructor
public class EventoAgendaService implements IEventoAgendaService {
    @Autowired
    private final EventoRepository eventoRepository;

    @Autowired
    private final AgendaRepository agendaRepository;

    @Override
    public Page<EventoAgendaResp> getAll(int page, int size, SortType sortType) {
        if (page < 0) page = 0;
        PageRequest pageRequest = null;

        switch (sortType) {
            case NONE -> pageRequest = PageRequest.of(page, size);
            case ASC -> pageRequest = PageRequest.of(page, size, Sort.by(FIEL_BY_SORT).ascending());
            case DESC -> pageRequest = PageRequest.of(page, size, Sort.by(FIEL_BY_SORT).descending());
        }

        Pageable pageable = pageRequest;
        return this.agendaRepository.findAll(pageable).map(this::entityToResponse);
    }

    @Override
    public EventoAgendaResp create(EventoAgendaReq request) {
        Agenda agenda = this.requestToEntity(request);

        Evento evento = Evento.builder()
                            .titulo(request.getTitulo())
                            .agendas(new ArrayList<>())
                            .build();

        eventoRepository.save(evento);

        agenda.setEvento(evento);

        return this.entityToResponse(this.agendaRepository.save(agenda));
    }

    @Override
    public EventoAgendaResp update(EventoAgendaReq request, Long id) {
        Agenda agenda = this.find(id);

        Evento evento = this.eventoRepository.findById(agenda.getEvento().getIdEvento())
        .orElseThrow(() -> new BadRequestException(ErrorMessages.idNotFound("Evento")));

        agenda = this.requestToEntity(request);

        agenda.setIdAgenda(id);
        agenda.setEvento(evento);

        return this.entityToResponse(this.agendaRepository.save(agenda));
    }

    @Override
    public void delete(Long id) {
        this.agendaRepository.delete(this.find(id));
    }

    @Override
    public EventoAgendaResp getById(Long id) {
        return this.entityToResponse(this.find(id));
    }

    private EventoAgendaResp entityToResponse(Agenda agenda) { 
        return EventoAgendaResp.builder()
                .titulo(agenda.getEvento().getTitulo())
                .descripcion(agenda.getEventTitle())
                .fechaInicio(agenda.getEventStartDate())
                .fechaFin(agenda.getEventEndDate())
                .horaInicio(agenda.getEventStartTime())
                .horaFin(agenda.getEventEndTime())
                .ubicacion(agenda.getEventLocation())
                .grupo(agenda.getGrupo().getNombre())
                .build();
    }

    private Agenda requestToEntity(EventoAgendaReq request) {
        Evento evento = Evento.builder()
                            .titulo(request.getTitulo())
                            .agendas(new ArrayList<>())
                            .build();

        return Agenda.builder()
                .eventTitle(request.getEventTitle())
                .eventStartDate(request.getEventStartDate())
                .eventEndDate(request.getEventEndDate())
                .eventStartTime(request.getEventStartTime())
                .eventEndTime(request.getEventEndTime())
                .evento(evento)
                .build();
    }

    private Agenda find(Long id) {
        return this.agendaRepository.findById(id).orElseThrow(() -> new BadRequestException(ErrorMessages.idNotFound("Evento")));
    }
}
