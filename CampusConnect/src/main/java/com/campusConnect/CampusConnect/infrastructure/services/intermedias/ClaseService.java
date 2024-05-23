package com.campusConnect.CampusConnect.infrastructure.services.intermedias;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.campusConnect.CampusConnect.api.dto.request.intermedias.ClaseReq;
import com.campusConnect.CampusConnect.api.dto.response.fuertes.intermedias.ClaseResp;
import com.campusConnect.CampusConnect.domain.repositories.intermedias.ClaseRepository;
import com.campusConnect.CampusConnect.infrastructure.abstract_services.intermedias.IClaseService;
import com.campusConnect.CampusConnect.util.enums.SortType;

import lombok.AllArgsConstructor;
import lombok.Data;

@Service
@Data
@AllArgsConstructor
public class ClaseService implements IClaseService {
    @Autowired
    private final ClaseRepository claseRepository;

    @Override
    public Page<ClaseResp> getAll(int page, int size, SortType sortType) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public ClaseResp create(ClaseReq request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public ClaseResp update(ClaseReq request, Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public ClaseResp getById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }
}
