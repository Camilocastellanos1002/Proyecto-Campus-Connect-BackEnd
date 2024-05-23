package com.campusConnect.CampusConnect.infrastructure.services.fuertes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.campusConnect.CampusConnect.api.dto.request.fuertes.UAdministradorReq;
import com.campusConnect.CampusConnect.api.dto.response.fuertes.UAdministradorResp;
import com.campusConnect.CampusConnect.domain.entities.debiles.Administrador;
import com.campusConnect.CampusConnect.domain.entities.fuertes.Usuario;
import com.campusConnect.CampusConnect.domain.repositories.debiles.AdministradorRepository;
import com.campusConnect.CampusConnect.domain.repositories.fuertes.UsuarioRepository;
import com.campusConnect.CampusConnect.infrastructure.abstract_services.fuertes.IAdministradorService;
import com.campusConnect.CampusConnect.util.enums.SortType;
import com.campusConnect.CampusConnect.util.exceptions.BadRequestException;
import com.campusConnect.CampusConnect.util.messages.ErrorMessages;

import lombok.AllArgsConstructor;
import lombok.Data;

@Service
@Data
@AllArgsConstructor
public class AdministradorService implements IAdministradorService {
    @Autowired
    private final UsuarioRepository usuarioRepository;

    @Autowired
    private final AdministradorRepository administradorRepository;

    @Override
    public Page<UAdministradorResp> getAll(int page, int size, SortType sortType) {
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
    public UAdministradorResp create(UAdministradorReq request) {
        Usuario usuario = this.requestToEntity(request);

        Administrador administrador = Administrador.builder().descripcionCargo(request.getDescripcionCargo()).build();
        
        administradorRepository.save(administrador);

        usuario.setAdministrador(administrador);

        return this.entityToResponse(this.usuarioRepository.save(usuario));
    }

    @Override
    public UAdministradorResp update(UAdministradorReq request, String id) {
        Usuario usuario = this.find(id);

        Administrador administrador = this.administradorRepository.findById(usuario.getAdministrador().getIdAdministrador())
        .orElseThrow(() -> new BadRequestException(ErrorMessages.idNotFound("Administrador")));

        usuario = this.requestToEntity(request);

        usuario.setIdUsuario(id);
        usuario.setAdministrador(administrador);

        return this.entityToResponse(this.usuarioRepository.save(usuario));
    }

    @Override
    public void delete(String id) {
        this.usuarioRepository.delete(this.find(id));
    }

    @Override
    public UAdministradorResp getById(String id) {
        return this.entityToResponse(this.find(id));
    }

    private UAdministradorResp entityToResponse(Usuario usuario) { 
        return UAdministradorResp.builder()
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
                .descripcionCargo(usuario.getAdministrador().getDescripcionCargo())
                .build();
    }

    private Usuario requestToEntity(UAdministradorReq request) {
        Administrador administrador = Administrador.builder().descripcionCargo(request.getDescripcionCargo()).build();

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
                .administrador(administrador)
                .build();
    }

    private Usuario find(String id) {
        return this.usuarioRepository.findById(id).orElseThrow(() -> new BadRequestException(ErrorMessages.idNotFound("Administrador")));
    }
}
