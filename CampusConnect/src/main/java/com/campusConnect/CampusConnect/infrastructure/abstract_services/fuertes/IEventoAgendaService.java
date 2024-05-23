package com.campusConnect.CampusConnect.infrastructure.abstract_services.fuertes;

import com.campusConnect.CampusConnect.api.dto.request.fuertes.EventoAgendaReq;
import com.campusConnect.CampusConnect.api.dto.response.fuertes.EventoAgendaResp;
import com.campusConnect.CampusConnect.infrastructure.abstract_services.CrudService;

public interface IEventoAgendaService extends CrudService<EventoAgendaReq, EventoAgendaResp, Long> {
    public final String FIEL_BY_SORT = "titulo";
}
