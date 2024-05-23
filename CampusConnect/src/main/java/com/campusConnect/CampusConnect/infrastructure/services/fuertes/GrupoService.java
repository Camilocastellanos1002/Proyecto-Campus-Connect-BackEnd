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

import com.campusConnect.CampusConnect.api.dto.request.fuertes.GrupoReq;
import com.campusConnect.CampusConnect.api.dto.response.fuertes.EstudianteToGrupoResp;
import com.campusConnect.CampusConnect.api.dto.response.fuertes.GrupoResp;
import com.campusConnect.CampusConnect.domain.entities.debiles.Estudiante;
import com.campusConnect.CampusConnect.domain.entities.fuertes.Grupo;
import com.campusConnect.CampusConnect.domain.entities.fuertes.Usuario;
import com.campusConnect.CampusConnect.domain.repositories.debiles.ProfesorRepository;
import com.campusConnect.CampusConnect.domain.repositories.fuertes.GrupoRepository;
import com.campusConnect.CampusConnect.domain.repositories.fuertes.UsuarioRepository;
import com.campusConnect.CampusConnect.infrastructure.abstract_services.fuertes.IGrupoService;
import com.campusConnect.CampusConnect.util.enums.SortType;
import com.campusConnect.CampusConnect.util.exceptions.BadRequestException;
import com.campusConnect.CampusConnect.util.messages.ErrorMessages;

import lombok.AllArgsConstructor;
import lombok.Data;

@Service
@Data
@AllArgsConstructor
public class GrupoService implements IGrupoService {
    @Autowired
    private final GrupoRepository grupoRepository;

    @Autowired
    private final UsuarioRepository usuarioRepository;

    @Autowired
    private final ProfesorRepository profesorRepository;

    @Override
    public Page<GrupoResp> getAll(int page, int size, SortType sortType) {
        if (page < 0) page = 0;
        PageRequest pageRequest = null;

        switch (sortType) {
            case NONE -> pageRequest = PageRequest.of(page, size);
            case ASC -> pageRequest = PageRequest.of(page, size, Sort.by(FIEL_BY_SORT).ascending());
            case DESC -> pageRequest = PageRequest.of(page, size, Sort.by(FIEL_BY_SORT).descending());
        }

        Pageable pageable = pageRequest;
        return this.grupoRepository.findAll(pageable).map(this::entityToResponse);
    }

    @Override
    public GrupoResp create(GrupoReq request) {
        Grupo grupo = this.requestToEntity(request);

        grupo.setEstudiantes(new ArrayList<>());

        return this.entityToResponse(this.grupoRepository.save(grupo));
    }

    @Override
    public GrupoResp update(GrupoReq request, Long id) {
        Grupo grupo = this.find(id);

        grupo = this.requestToEntity(request);
        
        grupo.setIdGrupo(id);
        grupo.setEstudiantes(grupo.getEstudiantes());

        return this.entityToResponse(this.grupoRepository.save(grupo));
    }

    @Override
    public void delete(Long id) {
        this.grupoRepository.delete(this.find(id));
    }

    @Override
    public GrupoResp getById(Long id) {
        return this.entityToResponse(this.find(id));
    }

    private GrupoResp entityToResponse(Grupo grupo) { 
        List<EstudianteToGrupoResp> estudiantes = grupo.getEstudiantes().stream().map(this::requestEstudianteToEntity).collect(Collectors.toList());

        return GrupoResp.builder()
                .nombre(grupo.getNombre())
                .nombreProfesor(grupo.getProfesor().getUsuario().getNombre())
                .apellidosProfesor(grupo.getProfesor().getUsuario().getApellidos())
                .estudiantes(estudiantes)
                .build();   
    }

    private Grupo requestToEntity(GrupoReq request) {
        return Grupo.builder()
                .nombre(request.getNombre())
                .profesor(findUsuario(request.getIdUsuario()).getProfesor())
                .build();
    }

    private EstudianteToGrupoResp requestEstudianteToEntity(Estudiante estudiante) {
        return EstudianteToGrupoResp.builder()
                .nombre(estudiante.getUsuario().getNombre())
                .apellidos(estudiante.getUsuario().getApellidos()).build();
    }

    private Grupo find(Long id) {
        return this.grupoRepository.findById(id).orElseThrow(() -> new BadRequestException(ErrorMessages.idNotFound("Grupo")));
    }

    private Usuario findUsuario(String id) {
        return this.usuarioRepository.findById(id).orElseThrow(() -> new BadRequestException(ErrorMessages.idNotFound("Profesor")));
    }  
}
