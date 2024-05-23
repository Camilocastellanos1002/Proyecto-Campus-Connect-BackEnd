package com.campusConnect.CampusConnect.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginReq {
    @NotBlank(message = "El usuario es obligatorio")
    @Size(min = 8, max = 150,message = "El usuario debe tener entre 8 y 150 caracteres")
    private String correo;
    @NotBlank(message = "La contraseña es obligatorio")
    @Size(min = 8, max = 150,message = "La contraseña debe tener entre 8 y 150 caracteres")
    // @Pattern(regexp = "expresion regular")
    private String password;
}
