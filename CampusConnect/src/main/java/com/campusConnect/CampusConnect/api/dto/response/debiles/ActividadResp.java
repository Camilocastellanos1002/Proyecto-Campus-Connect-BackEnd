package com.campusConnect.CampusConnect.api.dto.response.debiles;

import com.campusConnect.CampusConnect.api.dto.response.fuertes.intermedias.ClaseResp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActividadResp {
    private Long idActividad;
    private Float nota;
    private String descripcion;
    private ClaseResp clase;
}
