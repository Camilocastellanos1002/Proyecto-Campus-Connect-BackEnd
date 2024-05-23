package com.campusConnect.CampusConnect.api.dto.response.fuertes;

import java.sql.Date;
import java.sql.Time;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventoAgendaResp {
    private String titulo;
    private String descripcion;
    private Date fechaInicio;
    private Date fechaFin;
    private Time horaInicio;
    private Time horaFin;
    private String ubicacion;
    private String grupo;
}
