package com.campusConnect.CampusConnect.api.dto.request.intermedias;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClaseReq {
    @NotBlank(message = "El horario de la clase es requerido.")
    private String horario;

    @NotBlank(message = "El día de la semana de la clase es requerido.")
    private String diaSemana;

    @NotBlank(message = "El contenido de la clase es requerido.")
    private String contenido;

    @NotNull(message = "El id del grupo que ve esta clase es requerido.")
    private Long idGrupo;

    @NotNull(message = "El id de la asignatura que ve esta clase es requerido.")
    private Long idAsignatura;

    @NotNull(message = "El id del profesor que dicta esta clase es requerido.")
    private Long idProfesor;
}
