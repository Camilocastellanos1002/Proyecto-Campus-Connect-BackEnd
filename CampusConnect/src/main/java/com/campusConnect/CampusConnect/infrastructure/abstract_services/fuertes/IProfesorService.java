package com.campusConnect.CampusConnect.infrastructure.abstract_services.fuertes;

import com.campusConnect.CampusConnect.api.dto.request.fuertes.UProfesorReq;
import com.campusConnect.CampusConnect.api.dto.response.fuertes.UProfesorResp;
import com.campusConnect.CampusConnect.infrastructure.abstract_services.CrudService;

public interface IProfesorService extends CrudService<UProfesorReq, UProfesorResp, String> {
    public final String FIEL_BY_SORT = "nombre";
}
