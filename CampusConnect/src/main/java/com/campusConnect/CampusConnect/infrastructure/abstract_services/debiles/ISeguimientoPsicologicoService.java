package com.campusConnect.CampusConnect.infrastructure.abstract_services.debiles;

import com.campusConnect.CampusConnect.api.dto.request.debiles.SeguimientoPsicologicoReq;
import com.campusConnect.CampusConnect.api.dto.response.debiles.SeguimientoPsicologicoResp;
import com.campusConnect.CampusConnect.infrastructure.abstract_services.CrudService;

public interface ISeguimientoPsicologicoService extends CrudService<SeguimientoPsicologicoReq, SeguimientoPsicologicoResp, Long> {
    public final String FIEL_BY_SORT = "dia";
}
