package com.campusConnect.CampusConnect.infrastructure.services.intermedias;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.campusConnect.CampusConnect.api.dto.request.intermedias.AsistenciaReq;
import com.campusConnect.CampusConnect.api.dto.response.fuertes.intermedias.AsistenciaResp;
import com.campusConnect.CampusConnect.domain.repositories.intermedias.AsistenciaRepository;
import com.campusConnect.CampusConnect.infrastructure.abstract_services.intermedias.IAsistenciaService;
import com.campusConnect.CampusConnect.util.enums.SortType;

import lombok.AllArgsConstructor;
import lombok.Data;

@Service
@Data
@AllArgsConstructor
public class AsistenciaService implements IAsistenciaService {
    @Autowired
    private final AsistenciaRepository asistenciaRepository;

    @Override
    public Page<AsistenciaResp> getAll(int page, int size, SortType sortType) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public AsistenciaResp create(AsistenciaReq request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public AsistenciaResp update(AsistenciaReq request, Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public AsistenciaResp getById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    
}
