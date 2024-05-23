package com.campusConnect.CampusConnect.api.dto.response.fuertes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AsignaturaClaseResp {
    private Long idAsignatura;
    private String nombre;
    private String horario;
    private String diaSemana;
    private String contenido;
    private String grupo;
    private String profesor;
}
