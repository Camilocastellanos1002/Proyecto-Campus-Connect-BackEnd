package com.campusConnect.CampusConnect.api.dto.request.intermedias;

import java.util.Date;

import com.campusConnect.CampusConnect.util.enums.EstadoAsistencia;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AsistenciaReq {
    @NotNull(message = "La fecha es requerida para registrar la asistencia de la clase.")
    private Date dia;
    
    @NotNull(message = "El estado de asistencia es requerido.")
    private EstadoAsistencia asistencia;
    
    @NotNull(message = "El id de la clase es requerido.")
    private long idClase;
}
