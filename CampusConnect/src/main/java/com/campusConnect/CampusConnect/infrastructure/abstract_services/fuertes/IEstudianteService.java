package com.campusConnect.CampusConnect.infrastructure.abstract_services.fuertes;

import com.campusConnect.CampusConnect.api.dto.request.fuertes.UEstudianteReq;
import com.campusConnect.CampusConnect.api.dto.response.fuertes.UEstudianteResp;
import com.campusConnect.CampusConnect.infrastructure.abstract_services.CrudService;

public interface IEstudianteService extends CrudService<UEstudianteReq, UEstudianteResp, String> {
    public final String FIEL_BY_SORT = "nombre";
}
