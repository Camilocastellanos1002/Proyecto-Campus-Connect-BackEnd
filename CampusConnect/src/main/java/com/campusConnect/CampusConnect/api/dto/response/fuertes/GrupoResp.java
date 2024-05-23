package com.campusConnect.CampusConnect.api.dto.response.fuertes;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GrupoResp {
    private Long idGrupo;
    private String nombre;
    private String nombreProfesor;
    private String apellidosProfesor;
    private List<EstudianteToGrupoResp> estudiantes;
}
