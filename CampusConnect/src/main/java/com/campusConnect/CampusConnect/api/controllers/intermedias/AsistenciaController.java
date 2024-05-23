package com.campusConnect.CampusConnect.api.controllers.intermedias;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.campusConnect.CampusConnect.infrastructure.abstract_services.intermedias.IAsistenciaService;

import lombok.AllArgsConstructor;
import lombok.Data;

@RestController
@RequestMapping("/asistencia")
@Data
@AllArgsConstructor
public class AsistenciaController {
    /* Inyección de dependencias*/
    @Autowired
    private final IAsistenciaService asistenciaService;
}
