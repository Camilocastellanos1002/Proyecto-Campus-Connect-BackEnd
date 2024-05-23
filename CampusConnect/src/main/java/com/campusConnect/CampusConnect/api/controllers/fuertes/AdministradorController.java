package com.campusConnect.CampusConnect.api.controllers.fuertes;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.campusConnect.CampusConnect.api.dto.request.fuertes.UAdministradorReq;
import com.campusConnect.CampusConnect.api.dto.response.fuertes.UAdministradorResp;
import com.campusConnect.CampusConnect.infrastructure.abstract_services.fuertes.IAdministradorService;
import com.campusConnect.CampusConnect.util.enums.SortType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping(path = "/administrador")
@Data
@AllArgsConstructor
public class AdministradorController {
    /* Inyección de dependencias */
    @Autowired
    private final IAdministradorService administradorService;

    @GetMapping
    @Operation(summary = "Obtiene los usuarios de tipo administrador de la plataforma de forma páginada y organizada por nombre")
    public ResponseEntity<Page<UAdministradorResp>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestHeader(required = false) SortType sortType) {
        if (Objects.isNull(sortType)) {
            sortType = SortType.NONE;
        }

        return ResponseEntity.ok(this.administradorService.getAll(page - 1, size, sortType));
    }

    @PostMapping
    @Operation(summary = "Crea un administrador")
    @ApiResponse(responseCode = "400", description = "Cuando el id no es valido", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) })
    public ResponseEntity<UAdministradorResp> create(
            @Validated UAdministradorReq request) {
        return ResponseEntity.ok(this.administradorService.create(request));
    }

    @PutMapping(path = "/{id}")
    @Operation(summary = "Actualiza un administrador por id")
    @ApiResponse(responseCode = "400", description = "Cuando el id no es valido", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) })
    public ResponseEntity<UAdministradorResp> update(
            @PathVariable String id, @Validated @RequestBody UAdministradorReq request) {
        return ResponseEntity.ok(this.administradorService.update(request, id));
    }

    @DeleteMapping(path = "/{id}")
    @Operation(summary = "Elimina un administrador por id")
    @ApiResponse(responseCode = "400", description = "Cuando el id no es valido", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) })
    public ResponseEntity<Void> delete(@PathVariable String id) {
        this.administradorService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/{id}")
    @Operation(summary = "Obtiene un administrador por id")
    @ApiResponse(responseCode = "400", description = "Cuando el id no es valido", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) })
    public ResponseEntity<UAdministradorResp> getById(@PathVariable String id) {
        return ResponseEntity.ok(this.administradorService.getById(id));
    }
}
