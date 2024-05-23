package com.campusConnect.CampusConnect.api.dto.request.fuertes;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AsignaturaClaseReq {
    @NotBlank(message = "La nombre de la asignatura es requerida.")
    private String nombre;

    @NotBlank(message = "El horario de la asignatura es requerido.")
    private String horario;

    @NotBlank(message = "El día de la semana en el que se ve la asignatura es requerido.")
    private String diaSemana;

    @NotBlank(message = "El contenido de la asignatura es requerido.")
    private String contenido;
}
