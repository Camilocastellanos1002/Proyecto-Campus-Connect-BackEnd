package com.campusConnect.CampusConnect.infrastructure.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.campusConnect.CampusConnect.api.dto.request.LoginReq;
import com.campusConnect.CampusConnect.api.dto.request.fuertes.UAdministradorReq;
import com.campusConnect.CampusConnect.api.dto.request.fuertes.UEstudianteReq;
import com.campusConnect.CampusConnect.api.dto.request.fuertes.UProfesorReq;
import com.campusConnect.CampusConnect.api.dto.response.AuthResp;
import com.campusConnect.CampusConnect.domain.entities.debiles.Administrador;
import com.campusConnect.CampusConnect.domain.entities.debiles.Estudiante;
import com.campusConnect.CampusConnect.domain.entities.debiles.Profesor;
import com.campusConnect.CampusConnect.domain.entities.fuertes.Grupo;
import com.campusConnect.CampusConnect.domain.entities.fuertes.Usuario;
import com.campusConnect.CampusConnect.domain.repositories.debiles.AdministradorRepository;
import com.campusConnect.CampusConnect.domain.repositories.debiles.EstudianteRepository;
import com.campusConnect.CampusConnect.domain.repositories.debiles.ProfesorRepository;
import com.campusConnect.CampusConnect.domain.repositories.fuertes.UsuarioRepository;
import com.campusConnect.CampusConnect.infrastructure.abstract_services.IAuthService;
import com.campusConnect.CampusConnect.infrastructure.helpers.JwtService;
import com.campusConnect.CampusConnect.util.enums.Rol;
import com.campusConnect.CampusConnect.util.exceptions.BadRequestException;
import com.campusConnect.CampusConnect.util.messages.ErrorMessages;

import lombok.AllArgsConstructor;

@Transactional
@Service
@AllArgsConstructor
public class AuthService implements IAuthService {

    @Autowired
    private final UsuarioRepository usuarioRepository;

    @Autowired
    private final AdministradorRepository administradorRepository;

    @Autowired
    private final ProfesorRepository profesorRepository;

    @Autowired
    private final EstudianteRepository estudianteRepository;

    @Autowired
    private final JwtService jwtService;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResp login(LoginReq request){
        try {
             //autentica en la app el usario y contraseña
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getCorreo(), request.getPassword()));
        } catch (Exception e) {
            throw new BadRequestException("Credenciales invalidas");
        }
        //si el usuario se autentico correctamente
        Usuario usuario = this.findByCorreo(request.getCorreo());
        if (usuario == null) {
            throw new BadRequestException(ErrorMessages.idNotFound("Administrador"));
        }

        return AuthResp.builder()
                .message("Autenticado correctamente")
                .token(this.jwtService.getToken(usuario))
                .build();
    }

    /* Registro Administrador */
    @Override
    public AuthResp registerAdministrador(UAdministradorReq request) {
        /* Validar que el usuario no existe */
        Usuario exist = this.findByCorreo(request.getCorreo());

        if (exist != null) {
            throw new BadRequestException("Ya se encuentra un usuario registrado con este correo");
        }

        Administrador administrador = Administrador.builder().descripcionCargo(request.getDescripcionCargo()).build();
        
        administradorRepository.save(administrador);

        /* Construir el usuario */
        Usuario usuario = Usuario.builder()
                        .nombre(request.getNombre())
                        .apellidos(request.getApellidos())
                        .tipoDocumento(request.getTipoDocumento())
                        .documento(request.getDocumento())
                        .fechaNacimiento(request.getFechaNacimiento())
                        .telefono(request.getTelefono())
                        .correo(request.getCorreo())
                        .password(passwordEncoder.encode(request.getPassword())) //guardar la contraseña codificada
                        .rol(Rol.ADMINISTRADOR)
                        .foto(request.getFoto())    
                        .administrador(administrador)           
                        .build();
        /* Se Guarda el usuario en la db */
        Usuario usuarioSave = this.usuarioRepository.save(usuario);

        return AuthResp.builder()
                .message("Registro completado exitosamente")
                .token(this.jwtService.getToken(usuarioSave))
                .build();
    }

    /* Registro Estudiante */
    @Override
    public AuthResp registerEstudiante(UEstudianteReq request) {
        /* Validamos que el usuario no exista */
       Usuario exist = this.findByCorreo(request.getCorreo());

       if (exist != null) {
            throw new BadRequestException("Ya se encuentra un usuario registrado con este correo");
       }

       Estudiante estudiante = Estudiante.builder()
                                .acudientes(request.getAcudientes())
                                .seguimientosPsicologicos(new ArrayList<>())
                                .asistencias(new ArrayList<>())
                                .grupo(new Grupo())
                                .build();

       estudianteRepository.save(estudiante);

       /*Construimos el estudiante */
       Usuario usuario = Usuario.builder()
                        .nombre(request.getNombre())
                        .apellidos(request.getApellidos())
                        .tipoDocumento(request.getTipoDocumento())
                        .documento(request.getDocumento())
                        .fechaNacimiento(request.getFechaNacimiento())
                        .telefono(request.getTelefono())
                        .correo(request.getCorreo())
                        .password(passwordEncoder.encode(request.getPassword())) //guardar la contraseña codificada
                        .rol(Rol.ESTUDIANTE)              
                        .estudiante(estudiante)
                        .build();

        /* Se Guarda el usuario en la db */
        Usuario usuarioSave = this.usuarioRepository.save(usuario);

        return AuthResp.builder()
                .message("Registro completado exitosamente")
                .token(this.jwtService.getToken(usuarioSave))
                .build();
    }
    
    
    @Override
    public AuthResp registerProfesor(UProfesorReq request) {
        /* Validamos que el usuario no exista */
        Usuario exist = this.findByCorreo(request.getCorreo());

        if (exist != null) {
            throw new BadRequestException("Ya se encuentra un usuario registrado con este correo");
        }

        Profesor profesor = Profesor.builder()
                            .hojaVida(request.getHojaVida())
                            .grupo(new Grupo())
                            .clases(new ArrayList<>())
                            .build();

        profesorRepository.save(profesor);

        /*Construimos el estudiante */
        Usuario usuario = Usuario.builder()
                        .nombre(request.getNombre())
                        .apellidos(request.getApellidos())
                        .tipoDocumento(request.getTipoDocumento())
                        .documento(request.getDocumento())
                        .fechaNacimiento(request.getFechaNacimiento())
                        .telefono(request.getTelefono())
                        .correo(request.getCorreo())
                        .password(passwordEncoder.encode(request.getPassword())) // guardar la contraseña codificada
                        .rol(Rol.ESTUDIANTE)
                        .profesor(profesor)               
                        .build();

        /* Se Guarda el usuario en la db */
        Usuario usuarioSave = this.usuarioRepository.save(usuario);

        return AuthResp.builder()
                .message("Registro de estudiante completado exitosamente")
                .token(this.jwtService.getToken(usuarioSave))
                .build();
    }

    private Usuario findByCorreo(String userEmail){
        return this.usuarioRepository.findByCorreo(userEmail).orElse(null);
    }
}

/* Token generado por el usuario jccaste1002@gmail.com
 * eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjoiQURNSU5JU1RSQURPUiIsImlkIjoiYTRjMzYwNDktM2UxYi00YTg2LTg4YTctZjE2ODBkOTcyMDIyIiwic3ViIjoiY2FtaWxvY2FzdGVsbGFub3MxMDAyIiwiaWF0IjoxNzE1OTY0MzMyLCJleHAiOjE3MTYwNTA3MzJ9.w7m-f2ivb321kj4REAZj9fPmO15qbqD-Mhyb7JmcR6FtK4SNmlNK_s8_MqL3QY1bdtXhlB91DZ5zyU3EzF0Wlw
 */
