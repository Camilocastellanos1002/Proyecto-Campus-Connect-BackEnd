package com.campusConnect.CampusConnect.api.controllers.fuertes;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.campusConnect.CampusConnect.api.dto.request.fuertes.AsignaturaClaseReq;
import com.campusConnect.CampusConnect.api.dto.response.fuertes.AsignaturaClaseResp;
import com.campusConnect.CampusConnect.infrastructure.abstract_services.fuertes.IAsignaturaClaseService;
import com.campusConnect.CampusConnect.util.enums.SortType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

@RestController
@RequestMapping("/asignatura")
@Data
@AllArgsConstructor
public class AsignaturaController {
    /* Inyección de dependencias */
    @Autowired
    private final IAsignaturaClaseService service;

    @GetMapping
    @Operation(summary = "Obtiene las asignaturas de forma páginada y organizada por nombre")
    public ResponseEntity<Page<AsignaturaClaseResp>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestHeader(required = false) SortType sortType) {
        if (Objects.isNull(sortType)) {
            sortType = SortType.NONE;
        }

        return ResponseEntity.ok(this.service.getAll(page - 1, size, sortType));
    }

    @PostMapping
    @Operation(summary = "Crea una asignatura")
    @ApiResponse(responseCode = "400", description = "Cuando el id no es valido", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) })
    public ResponseEntity<AsignaturaClaseResp> create(
            @Validated AsignaturaClaseReq request) {
        return ResponseEntity.ok(this.service.create(request));
    }

    @PutMapping(path = "/{id}")
    @Operation(summary = "Actualiza una asignatura por id")
    @ApiResponse(responseCode = "400", description = "Cuando el id no es valido", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) })
    public ResponseEntity<AsignaturaClaseResp> update(
            @PathVariable Long id, @Validated @RequestBody AsignaturaClaseReq request) {
        return ResponseEntity.ok(this.service.update(request, id));
    }

    @DeleteMapping(path = "/{id}")
    @Operation(summary = "Elimina una asignatura por id")
    @ApiResponse(responseCode = "400", description = "Cuando el id no es valido", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.service.delete(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/{id}")
    @Operation(summary = "Obtiene una asignatura por id")
    @ApiResponse(responseCode = "400", description = "Cuando el id no es valido", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) })
    public ResponseEntity<AsignaturaClaseResp> getById(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.getById(id));
    }
}
