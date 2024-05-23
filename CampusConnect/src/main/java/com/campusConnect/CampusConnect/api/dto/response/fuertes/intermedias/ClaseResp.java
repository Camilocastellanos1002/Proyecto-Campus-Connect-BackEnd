package com.campusConnect.CampusConnect.api.dto.response.fuertes.intermedias;

import java.util.List;

import com.campusConnect.CampusConnect.api.dto.response.debiles.ActividadResp;
import com.campusConnect.CampusConnect.api.dto.response.fuertes.AsignaturaClaseResp;
import com.campusConnect.CampusConnect.api.dto.response.fuertes.GrupoResp;
import com.campusConnect.CampusConnect.api.dto.response.fuertes.UProfesorResp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClaseResp {
    private Long idClase;
    private String horario;
    private String diaSemana;
    private String contenido;
    private List<ActividadResp> actividades;
    private List<AsistenciaResp> asistencias;
    private GrupoResp grupo;
    private AsignaturaClaseResp asignatura;
    private UProfesorResp profesor;
}
