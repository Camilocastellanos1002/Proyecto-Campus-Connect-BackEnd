package com.campusConnect.CampusConnect.api.dto.response.debiles;

import java.util.Date;

import com.campusConnect.CampusConnect.util.enums.EstadoAnimo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeguimientoPsicologicoResp {
    private Long idSeguimientoPsicologico;
    private Date dia;
    private EstadoAnimo estadoAnimo;
    private String nombreEstudiante;
    private String apellidosEstudiante;
}
