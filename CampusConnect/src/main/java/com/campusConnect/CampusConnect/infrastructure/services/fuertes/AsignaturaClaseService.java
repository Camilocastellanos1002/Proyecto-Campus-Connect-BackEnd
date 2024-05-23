package com.campusConnect.CampusConnect.infrastructure.services.fuertes;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.campusConnect.CampusConnect.api.dto.request.fuertes.AsignaturaClaseReq;
import com.campusConnect.CampusConnect.api.dto.response.fuertes.AsignaturaClaseResp;
import com.campusConnect.CampusConnect.domain.entities.debiles.Asignatura;
import com.campusConnect.CampusConnect.domain.entities.intermedias.Clase;
import com.campusConnect.CampusConnect.domain.repositories.debiles.AsignaturaRepository;
import com.campusConnect.CampusConnect.domain.repositories.intermedias.ClaseRepository;
import com.campusConnect.CampusConnect.infrastructure.abstract_services.fuertes.IAsignaturaClaseService;
import com.campusConnect.CampusConnect.util.enums.SortType;
import com.campusConnect.CampusConnect.util.exceptions.BadRequestException;
import com.campusConnect.CampusConnect.util.messages.ErrorMessages;

import lombok.AllArgsConstructor;
import lombok.Data;

@Service
@Data
@AllArgsConstructor
public class AsignaturaClaseService implements IAsignaturaClaseService {
    @Autowired
    private final ClaseRepository claseRepository;

    @Autowired
    private final AsignaturaRepository asignaturaRepository;

    @Override
    public Page<AsignaturaClaseResp> getAll(int page, int size, SortType sortType) {
        if (page < 0) page = 0;
        PageRequest pageRequest = null;

        switch (sortType) {
            case NONE -> pageRequest = PageRequest.of(page, size);
            case ASC -> pageRequest = PageRequest.of(page, size, Sort.by(FIEL_BY_SORT).ascending());
            case DESC -> pageRequest = PageRequest.of(page, size, Sort.by(FIEL_BY_SORT).descending());
        }

        Pageable pageable = pageRequest;
        return this.claseRepository.findAll(pageable).map(this::entityToResponse);
    }

    @Override
    public AsignaturaClaseResp create(AsignaturaClaseReq request) {
        Clase clase = this.requestToEntity(request);

        Asignatura asignatura = Asignatura.builder()
                                .nombre(request.getNombre())
                                .clases(new ArrayList<>())
                                .build();

        asignaturaRepository.save(asignatura);

        clase.setAsignatura(asignatura);

        return this.entityToResponse(this.claseRepository.save(clase));
    }

    @Override
    public AsignaturaClaseResp update(AsignaturaClaseReq request, Long id) {
        Clase clase = this.find(id);

        Asignatura asignatura = this.asignaturaRepository.findById(clase.getAsignatura().getIdAsignatura())
        .orElseThrow(() -> new BadRequestException(ErrorMessages.idNotFound("Asignatura")));

        clase = this.requestToEntity(request);

        clase.setIdClase(id);
        clase.setAsignatura(asignatura);

        return this.entityToResponse(this.claseRepository.save(clase));
    }

    @Override
    public void delete(Long id) {
        this.claseRepository.delete(this.find(id));
    }

    @Override
    public AsignaturaClaseResp getById(Long id) {
        return this.entityToResponse(this.find(id));
    }

    private AsignaturaClaseResp entityToResponse(Clase clase) { 
        return AsignaturaClaseResp.builder()
                .nombre(clase.getAsignatura().getNombre())
                .horario(clase.getHorario())
                .diaSemana(clase.getDiaSemana())
                .contenido(clase.getContenido())
                .build();
    }

    private Clase requestToEntity(AsignaturaClaseReq request) {
        Asignatura asignatura = Asignatura.builder()
                                .nombre(request.getNombre())
                                .clases(new ArrayList<>())
                                .build();

        return Clase.builder()
                .horario(request.getHorario())
                .diaSemana(request.getDiaSemana())
                .contenido(request.getContenido())
                .asignatura(asignatura)
                .build();
    }

    private Clase find(Long id) {
        return this.claseRepository.findById(id).orElseThrow(() -> new BadRequestException(ErrorMessages.idNotFound("Asignatura")));
    }
}
