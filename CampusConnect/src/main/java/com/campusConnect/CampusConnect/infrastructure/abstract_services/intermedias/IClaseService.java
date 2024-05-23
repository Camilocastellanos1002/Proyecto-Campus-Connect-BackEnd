package com.campusConnect.CampusConnect.infrastructure.abstract_services.intermedias;

import com.campusConnect.CampusConnect.api.dto.request.intermedias.ClaseReq;
import com.campusConnect.CampusConnect.api.dto.response.fuertes.intermedias.ClaseResp;
import com.campusConnect.CampusConnect.infrastructure.abstract_services.CrudService;

public interface IClaseService extends CrudService <ClaseReq, ClaseResp, Long> {
    
}
