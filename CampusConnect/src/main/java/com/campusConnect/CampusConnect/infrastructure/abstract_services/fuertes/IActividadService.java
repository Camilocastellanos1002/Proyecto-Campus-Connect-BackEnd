package com.campusConnect.CampusConnect.infrastructure.abstract_services.fuertes;

import com.campusConnect.CampusConnect.api.dto.request.fuertes.ActividadReq;
import com.campusConnect.CampusConnect.api.dto.response.debiles.ActividadResp;
import com.campusConnect.CampusConnect.infrastructure.abstract_services.CrudService;

public interface IActividadService extends CrudService<ActividadReq, ActividadResp, Long> {
    
}
