package com.campusConnect.CampusConnect.infrastructure.abstract_services.intermedias;

import com.campusConnect.CampusConnect.api.dto.request.intermedias.AsistenciaReq;
import com.campusConnect.CampusConnect.api.dto.response.fuertes.intermedias.AsistenciaResp;
import com.campusConnect.CampusConnect.infrastructure.abstract_services.CrudService;

public interface IAsistenciaService extends CrudService <AsistenciaReq, AsistenciaResp, Long> {
    
}
