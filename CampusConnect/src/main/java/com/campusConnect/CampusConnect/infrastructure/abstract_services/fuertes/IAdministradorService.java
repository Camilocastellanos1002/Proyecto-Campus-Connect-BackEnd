package com.campusConnect.CampusConnect.infrastructure.abstract_services.fuertes;

import com.campusConnect.CampusConnect.api.dto.request.fuertes.UAdministradorReq;
import com.campusConnect.CampusConnect.api.dto.response.fuertes.UAdministradorResp;
import com.campusConnect.CampusConnect.infrastructure.abstract_services.CrudService;

public interface IAdministradorService extends CrudService<UAdministradorReq, UAdministradorResp, String> {
    public final String FIEL_BY_SORT = "nombre";
}
