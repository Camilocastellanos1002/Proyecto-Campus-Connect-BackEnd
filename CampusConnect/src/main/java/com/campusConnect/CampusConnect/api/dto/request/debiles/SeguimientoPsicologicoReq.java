package com.campusConnect.CampusConnect.api.dto.request.debiles;

import java.util.Date;

import com.campusConnect.CampusConnect.util.enums.EstadoAnimo;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeguimientoPsicologicoReq {
    @NotNull(message = "La fecha del registro del seguimiento psicológico es requerida.")
    private Date dia;

    @NotNull(message = "El estado de ánimo del estudiante es requerido.")
    private EstadoAnimo estadoAnimo;

    @NotNull(message = "El id del estudiante que registra realiza el registro es requerido.")
    private String idEstudiante;
}
