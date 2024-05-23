package com.campusConnect.CampusConnect.api.dto.request.fuertes;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
public class ActividadReq {
    @Min(value = 0, message = "La nota mínima permitida es 0.")
    @Max(value = 5, message = "La nota máxima permitida es 5.")
    private Float nota;

    @NotBlank(message = "La descripción de la actividad es requerida.")
    private String descripcion;

    /* Clase a la que pertenece la actividad */
    @NotNull(message = "El id de la clase a la que pertenece esta actividad es obligatoria.")
    private Long idClase;
}

