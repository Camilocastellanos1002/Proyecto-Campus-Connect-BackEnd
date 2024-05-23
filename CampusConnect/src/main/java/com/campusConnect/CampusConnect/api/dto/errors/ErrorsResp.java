package com.campusConnect.CampusConnect.api.dto.errors;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder // Llama el constructor del padre
@EqualsAndHashCode(callSuper = true) // Para que el padre sea el unico que ponga el serial, no genere dos espacios de memoria
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorsResp extends BaseErrorResponse {
    private List<Map<String,String>> errors; // Lista de map u objetos de errores
}