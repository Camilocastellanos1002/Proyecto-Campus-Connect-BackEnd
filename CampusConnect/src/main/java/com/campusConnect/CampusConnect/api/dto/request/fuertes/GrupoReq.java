package com.campusConnect.CampusConnect.api.dto.request.fuertes;

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
public class GrupoReq {
    @NotBlank(message = "El nombre del grupo es requerido.")
    private String nombre;

    @NotNull(message = "El id del profesor director de grupo es obligatorio.")
    private String idUsuario;
}
