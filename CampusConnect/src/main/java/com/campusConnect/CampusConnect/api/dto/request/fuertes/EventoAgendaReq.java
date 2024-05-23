package com.campusConnect.CampusConnect.api.dto.request.fuertes;

import java.sql.Date;
import java.sql.Time;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventoAgendaReq {
    @NotBlank(message = "El título del evento es requerido.")
    @Size(max = 255, message = "El título del evento no puede superar los 255 caracteres.")
    private String titulo;

    @NotNull(message = "La descripción del evento es requerida.")
    private String eventTitle;

    @NotNull(message = "La fecha de inicio del evento es requerida.")
    private Date eventStartDate;

    @NotNull(message = "La fecha de finalización del evento es requerida.")
    private Date eventEndDate;

    @NotNull(message = "La hora de inicio del evento es requerida.")
    private Time eventStartTime;

    @NotNull(message = "La hora de finalización del evento es requerida.")
    private Time eventEndTime;

    @NotNull(message = "La ubicación del evento es requerida.")
    private String eventLocation;

}
