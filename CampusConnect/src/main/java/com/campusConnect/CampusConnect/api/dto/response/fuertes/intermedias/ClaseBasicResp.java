package com.campusConnect.CampusConnect.api.dto.response.fuertes.intermedias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClaseBasicResp {
    private Long idClase;
    private String horario;
    private String diaSemana;
    private String contenido;
}
