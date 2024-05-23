package com.campusConnect.CampusConnect.api.dto.response.fuertes;

import java.math.BigInteger;
// import java.util.Date;
import java.util.List;

import com.campusConnect.CampusConnect.util.enums.Rol;
import com.campusConnect.CampusConnect.util.enums.TipoDocumento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UProfesorResp {
    private String idUsuario;
    private String nombre;
    private String apellidos;
    private TipoDocumento tipoDocumento;
    private BigInteger documento;
    private String fechaNacimiento;
    private String correo;
    private String telefono;
    private Rol rol;
    private String password;
    private String foto;
    private String hojaVida;
    private String grupo;
    private List<String> clases;
}
