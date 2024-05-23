package com.campusConnect.CampusConnect.infrastructure.services.fuertes;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.campusConnect.CampusConnect.api.dto.request.fuertes.UEstudianteReq;
import com.campusConnect.CampusConnect.api.dto.response.fuertes.UEstudianteResp;
import com.campusConnect.CampusConnect.domain.entities.debiles.Estudiante;
import com.campusConnect.CampusConnect.domain.entities.fuertes.Grupo;
import com.campusConnect.CampusConnect.domain.entities.fuertes.Usuario;
import com.campusConnect.CampusConnect.domain.repositories.debiles.EstudianteRepository;
import com.campusConnect.CampusConnect.domain.repositories.fuertes.UsuarioRepository;
import com.campusConnect.CampusConnect.infrastructure.abstract_services.fuertes.IEstudianteService;
import com.campusConnect.CampusConnect.util.enums.SortType;
import com.campusConnect.CampusConnect.util.exceptions.BadRequestException;
import com.campusConnect.CampusConnect.util.messages.ErrorMessages;

import lombok.AllArgsConstructor;
import lombok.Data;

@Service
@Data
@AllArgsConstructor
public class EstudianteService implements IEstudianteService {
    @Autowired
    private final UsuarioRepository usuarioRepository;
    
    @Autowired
    private final EstudianteRepository estudianteRepository;

    @Override
    public Page<UEstudianteResp> getAll(int page, int size, SortType sortType) {
        if (page < 0) page = 0;
        PageRequest pageRequest = null;

        switch (sortType) {
            case NONE -> pageRequest = PageRequest.of(page, size);
            case ASC -> pageRequest = PageRequest.of(page, size, Sort.by(FIEL_BY_SORT).ascending());
            case DESC -> pageRequest = PageRequest.of(page, size, Sort.by(FIEL_BY_SORT).descending());
        }

        Pageable pageable = pageRequest;
        return this.usuarioRepository.findAll(pageable).map(this::entityToResponse);
    }

    @Override
    public UEstudianteResp create(UEstudianteReq request) {
        Usuario usuario = this.requestToEntity(request);

        Estudiante estudiante = Estudiante.builder()
                                .acudientes(request.getAcudientes())
                                .seguimientosPsicologicos(new ArrayList<>())
                                .asistencias(new ArrayList<>())
                                .grupo(new Grupo())
                                .build();

        estudianteRepository.save(estudiante);

        usuario.setEstudiante(estudiante);

        return this.entityToResponse(this.usuarioRepository.save(usuario));
    }

    @Override
    public UEstudianteResp update(UEstudianteReq request, String id) {
        Usuario usuario = this.find(id);

        Estudiante estudiante = this.estudianteRepository.findById(usuario.getEstudiante().getIdEstudiante())
        .orElseThrow(() -> new BadRequestException(ErrorMessages.idNotFound("Estudiante")));

        usuario = this.requestToEntity(request);

        usuario.setIdUsuario(id);
        usuario.setEstudiante(estudiante);

        return this.entityToResponse(this.usuarioRepository.save(usuario));
    }

    @Override
    public void delete(String id) {
        this.usuarioRepository.delete(this.find(id));
    }

    @Override
    public UEstudianteResp getById(String id) {
        return this.entityToResponse(this.find(id));
    }

    private UEstudianteResp entityToResponse(Usuario usuario) { 
        return UEstudianteResp.builder()
                .nombre(usuario.getNombre())
                .apellidos(usuario.getApellidos())
                .tipoDocumento(usuario.getTipoDocumento())
                .documento(usuario.getDocumento())
                .fechaNacimiento(usuario.getFechaNacimiento())
                .correo(usuario.getCorreo())
                .telefono(usuario.getTelefono())
                .rol(usuario.getRol())
                .password(usuario.getPassword())
                .foto(usuario.getFoto())
                .acudientes(usuario.getEstudiante().getAcudientes())
                .grupo(usuario.getEstudiante().getGrupo().getNombre())
                .build();
    }

    private Usuario requestToEntity(UEstudianteReq request) {
        Estudiante estudiante = Estudiante.builder()
                            .acudientes(request.getAcudientes())
                            .seguimientosPsicologicos(new ArrayList<>())
                            .asistencias(new ArrayList<>())
                            .grupo(new Grupo())
                            .build();

        return Usuario.builder()
                .nombre(request.getNombre())
                .apellidos(request.getApellidos())
                .tipoDocumento(request.getTipoDocumento())
                .documento(request.getDocumento())
                .fechaNacimiento(request.getFechaNacimiento())
                .correo(request.getCorreo())
                .telefono(request.getTelefono())
                .rol(request.getRol())
                .password(request.getPassword())
                .foto(request.getFoto())
                .estudiante(estudiante)
                .build();
    }

    private Usuario find(String id) {
        return this.usuarioRepository.findById(id).orElseThrow(() -> new BadRequestException(ErrorMessages.idNotFound("Estudiante")));
    }
}
