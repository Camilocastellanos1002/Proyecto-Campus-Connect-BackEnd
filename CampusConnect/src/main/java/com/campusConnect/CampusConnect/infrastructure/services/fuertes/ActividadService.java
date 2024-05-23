package com.campusConnect.CampusConnect.infrastructure.services.fuertes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.campusConnect.CampusConnect.api.dto.request.fuertes.ActividadReq;
import com.campusConnect.CampusConnect.api.dto.response.debiles.ActividadResp;
import com.campusConnect.CampusConnect.domain.repositories.debiles.ActividadRepository;
import com.campusConnect.CampusConnect.infrastructure.abstract_services.fuertes.IActividadService;
import com.campusConnect.CampusConnect.util.enums.SortType;

import lombok.AllArgsConstructor;
import lombok.Data;

@Service
@Data
@AllArgsConstructor
public class ActividadService implements IActividadService {
    @Autowired
    private final ActividadRepository actividadRepository;
    

    @Override
    public Page<ActividadResp> getAll(int page, int size, SortType sortType) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public ActividadResp create(ActividadReq request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public ActividadResp update(ActividadReq request, Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public ActividadResp getById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }
}
