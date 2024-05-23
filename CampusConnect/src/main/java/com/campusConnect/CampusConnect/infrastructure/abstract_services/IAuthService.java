package com.campusConnect.CampusConnect.infrastructure.abstract_services;

import com.campusConnect.CampusConnect.api.dto.request.LoginReq;
import com.campusConnect.CampusConnect.api.dto.request.fuertes.UAdministradorReq;
import com.campusConnect.CampusConnect.api.dto.request.fuertes.UEstudianteReq;
import com.campusConnect.CampusConnect.api.dto.request.fuertes.UProfesorReq;
import com.campusConnect.CampusConnect.api.dto.response.AuthResp;

public interface IAuthService {
    public AuthResp login(LoginReq request);

    public AuthResp registerAdministrador(UAdministradorReq request);
    
    public AuthResp registerEstudiante(UEstudianteReq request);
    
    public AuthResp registerProfesor(UProfesorReq request);
}
