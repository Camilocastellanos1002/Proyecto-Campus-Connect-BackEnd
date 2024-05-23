package com.campusConnect.CampusConnect.infrastructure.services.fuertes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.campusConnect.CampusConnect.api.dto.request.fuertes.UProfesorReq;
import com.campusConnect.CampusConnect.api.dto.response.fuertes.UProfesorResp;
import com.campusConnect.CampusConnect.domain.entities.debiles.Profesor;
import com.campusConnect.CampusConnect.domain.entities.fuertes.Grupo;
import com.campusConnect.CampusConnect.domain.entities.fuertes.Usuario;
import com.campusConnect.CampusConnect.domain.repositories.debiles.ProfesorRepository;
import com.campusConnect.CampusConnect.domain.repositories.fuertes.UsuarioRepository;
import com.campusConnect.CampusConnect.infrastructure.abstract_services.fuertes.IProfesorService;
import com.campusConnect.CampusConnect.util.enums.SortType;
import com.campusConnect.CampusConnect.util.exceptions.BadRequestException;
import com.campusConnect.CampusConnect.util.messages.ErrorMessages;

import lombok.AllArgsConstructor;
import lombok.Data;

@Service
@Data
@AllArgsConstructor
public class ProfesorService implements IProfesorService {
    @Autowired
    private final UsuarioRepository usuarioRepository;

    @Autowired
    private final ProfesorRepository profesorRepository;

    @Override
    public Page<UProfesorResp> getAll(int page, int size, SortType sortType) {
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
    public UProfesorResp create(UProfesorReq request) {
        Usuario usuario = this.requestToEntity(request);

        Profesor profesor = Profesor.builder()
                            .hojaVida(request.getHojaVida())
                            .grupo(new Grupo())
                            .clases(new ArrayList<>())
                            .build();

        profesorRepository.save(profesor);

        usuario.setProfesor(profesor);

        return this.entityToResponse(this.usuarioRepository.save(usuario));
    }

    @Override
    public UProfesorResp update(UProfesorReq request, String id) {
        Usuario usuario = this.find(id);

        Profesor profesor = this.profesorRepository.findById(usuario.getProfesor().getIdProfesor())
        .orElseThrow(() -> new BadRequestException(ErrorMessages.idNotFound("Profesor")));

        usuario = this.requestToEntity(request);

        usuario.setIdUsuario(id);
        usuario.setProfesor(profesor);

        return this.entityToResponse(this.usuarioRepository.save(usuario));
    }

    @Override
    public void delete(String id) {
        this.usuarioRepository.delete(this.find(id));
    }

    @Override
    public UProfesorResp getById(String id) {
        return this.entityToResponse(this.find(id));
    }

    private UProfesorResp entityToResponse(Usuario usuario) { 
        Profesor profesor = usuario.getProfesor();
        
        List<String> asignaturas = profesor.getClases().stream().map(clases -> clases.getAsignatura().getNombre()).collect(Collectors.toList());

        return UProfesorResp.builder()
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
                .hojaVida(usuario.getProfesor().getHojaVida())
                .grupo(usuario.getProfesor().getGrupo().getNombre())
                .clases(asignaturas)
                .build();
    }

    private Usuario requestToEntity(UProfesorReq request) {
        Profesor profesor = Profesor.builder()
                            .hojaVida(request.getHojaVida())
                            .grupo(new Grupo())
                            .clases(new ArrayList<>())
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
                .profesor(profesor)
                .build();
    }

    private Usuario find(String id) {
        return this.usuarioRepository.findById(id).orElseThrow(() -> new BadRequestException(ErrorMessages.idNotFound("Profesor")));
    }

}
