package com.campusConnect.CampusConnect.infrastructure.abstract_services.fuertes;

import com.campusConnect.CampusConnect.api.dto.request.fuertes.AsignaturaClaseReq;
import com.campusConnect.CampusConnect.api.dto.response.fuertes.AsignaturaClaseResp;
import com.campusConnect.CampusConnect.infrastructure.abstract_services.CrudService;

public interface IAsignaturaClaseService extends CrudService<AsignaturaClaseReq, AsignaturaClaseResp, Long> {
    public final String FIEL_BY_SORT = "nombre";
}
