package com.campusConnect.CampusConnect.infrastructure.services.debiles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.campusConnect.CampusConnect.api.dto.request.debiles.SeguimientoPsicologicoReq;
import com.campusConnect.CampusConnect.api.dto.response.debiles.SeguimientoPsicologicoResp;
import com.campusConnect.CampusConnect.domain.entities.debiles.SeguimientoPsicologico;
import com.campusConnect.CampusConnect.domain.entities.fuertes.Usuario;
import com.campusConnect.CampusConnect.domain.repositories.debiles.SeguimientoPsicologicoRepository;
import com.campusConnect.CampusConnect.domain.repositories.fuertes.UsuarioRepository;
import com.campusConnect.CampusConnect.infrastructure.abstract_services.debiles.ISeguimientoPsicologicoService;
import com.campusConnect.CampusConnect.util.enums.SortType;
import com.campusConnect.CampusConnect.util.exceptions.BadRequestException;
import com.campusConnect.CampusConnect.util.messages.ErrorMessages;

import lombok.AllArgsConstructor;
import lombok.Data;

@Service
@Data
@AllArgsConstructor
public class SeguimientoPsicologicoService implements ISeguimientoPsicologicoService {
    @Autowired
    private final SeguimientoPsicologicoRepository repository;

    @Autowired
    private final UsuarioRepository usuarioRepository;

    @Override
    public Page<SeguimientoPsicologicoResp> getAll(int page, int size, SortType sortType) {
                if (page < 0) page = 0;
        PageRequest pageRequest = null;

        switch (sortType) {
            case NONE -> pageRequest = PageRequest.of(page, size);
            case ASC -> pageRequest = PageRequest.of(page, size, Sort.by(FIEL_BY_SORT).ascending());
            case DESC -> pageRequest = PageRequest.of(page, size, Sort.by(FIEL_BY_SORT).descending());
        }

        Pageable pageable = pageRequest;
        return this.repository.findAll(pageable).map(this::entityToResponse);
    }

    @Override
    public SeguimientoPsicologicoResp create(SeguimientoPsicologicoReq request) {
        SeguimientoPsicologico seguimiento = this.requestToEntity(request);

        return this.entityToResponse(this.repository.save(seguimiento));
    }

    @Override
    public SeguimientoPsicologicoResp update(SeguimientoPsicologicoReq request, Long id) {
        SeguimientoPsicologico seguimiento = this.find(id);

        seguimiento = this.requestToEntity(request);
        
        seguimiento.setIdSeguimientoPsicologico(id);
        seguimiento.setEstudiante(seguimiento.getEstudiante());

        return this.entityToResponse(this.repository.save(seguimiento));
    }

    @Override
    public void delete(Long id) {
        this.repository.delete(this.find(id));
    }

    @Override
    public SeguimientoPsicologicoResp getById(Long id) {
        return this.entityToResponse(this.find(id));
    }

    private SeguimientoPsicologicoResp entityToResponse(SeguimientoPsicologico entity) { 
        return SeguimientoPsicologicoResp.builder()
                .dia(entity.getDia())
                .estadoAnimo(entity.getEstadoAnimo())
                .nombreEstudiante(entity.getEstudiante().getUsuario().getNombre())
                .apellidosEstudiante(entity.getEstudiante().getUsuario().getApellidos())
                .build();   
    }

    private SeguimientoPsicologico requestToEntity(SeguimientoPsicologicoReq request) {
        return SeguimientoPsicologico.builder()
                .dia(request.getDia())
                .estadoAnimo(request.getEstadoAnimo())
                .estudiante(findUsuario(request.getIdEstudiante()).getEstudiante())
                .build();
    }

    private SeguimientoPsicologico find(Long id) {
        return this.repository.findById(id).orElseThrow(() -> new BadRequestException(ErrorMessages.idNotFound("Grupo")));
    }

    private Usuario findUsuario(String id) {
        return this.usuarioRepository.findById(id).orElseThrow(() -> new BadRequestException(ErrorMessages.idNotFound("Estudiante")));
    }
}
