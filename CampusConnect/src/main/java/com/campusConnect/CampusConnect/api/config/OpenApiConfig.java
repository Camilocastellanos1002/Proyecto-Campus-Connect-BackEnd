package com.campusConnect.CampusConnect.api.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "API para administrar entidades de un colegio (Estudiantes, Docentes, Profesores)",
        version = "1.0",
        description = "Documentacion API de adminitracion de entidades de un colegio"
    ))
public class OpenApiConfig {
    
}
