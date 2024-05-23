package com.campusConnect.CampusConnect.infrastructure.abstract_services.fuertes;

import com.campusConnect.CampusConnect.api.dto.request.fuertes.GrupoReq;
import com.campusConnect.CampusConnect.api.dto.response.fuertes.GrupoResp;
import com.campusConnect.CampusConnect.infrastructure.abstract_services.CrudService;

public interface IGrupoService extends CrudService<GrupoReq, GrupoResp, Long> {
    public final String FIEL_BY_SORT = "nombre";
}
