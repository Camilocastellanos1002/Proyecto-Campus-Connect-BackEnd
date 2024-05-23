package com.campusConnect.CampusConnect.api.dto.response.debiles;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActividadBasicResp {
    private Long idActividad;
    private Float nota;
    private String descripcion;
}
