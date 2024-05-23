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

import com.campusConnect.CampusConnect.api.dto.request.fuertes.UEstudianteReq;
import com.campusConnect.CampusConnect.api.dto.response.fuertes.UEstudianteResp;
import com.campusConnect.CampusConnect.infrastructure.abstract_services.fuertes.IEstudianteService;
import com.campusConnect.CampusConnect.util.enums.SortType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

@RestController
@RequestMapping("/estudiante")
@Data
@AllArgsConstructor
public class EstudianteController {
        /* Inyección de dependencias */
        @Autowired
        private final IEstudianteService estudianteService;

        @GetMapping
        @Operation(summary = "Obtiene los usuarios de tipo estudiante de la plataforma de forma páginada y organizada por nombre")
        public ResponseEntity<Page<UEstudianteResp>> getAll(
                        @RequestParam(defaultValue = "1") int page,
                        @RequestParam(defaultValue = "5") int size,
                        @RequestHeader(required = false) SortType sortType) {
                if (Objects.isNull(sortType)) {
                        sortType = SortType.NONE;
                }

                return ResponseEntity.ok(this.estudianteService.getAll(page - 1, size, sortType));
        }

        @PostMapping
        @Operation(summary = "Crea un estudiante")
        @ApiResponse(responseCode = "400", description = "Cuando el id no es valido", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) })
        public ResponseEntity<UEstudianteResp> create(
                        @Validated UEstudianteReq request) {
                return ResponseEntity.ok(this.estudianteService.create(request));
        }

        @PutMapping(path = "/{id}")
        @Operation(summary = "Actualiza un estudiante por id")
        @ApiResponse(responseCode = "400", description = "Cuando el id no es valido", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) })
        public ResponseEntity<UEstudianteResp> update(
                        @PathVariable String id, @Validated @RequestBody UEstudianteReq request) {
                return ResponseEntity.ok(this.estudianteService.update(request, id));
        }

        @DeleteMapping(path = "/{id}")
        @Operation(summary = "Elimina un estudiante por id")
        @ApiResponse(responseCode = "400", description = "Cuando el id no es valido", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) })
        public ResponseEntity<Void> delete(@PathVariable String id) {
                this.estudianteService.delete(id);

                return ResponseEntity.noContent().build();
        }

        @GetMapping(path = "/{id}")
        @Operation(summary = "Obtiene un estudiante por id")
        @ApiResponse(responseCode = "400", description = "Cuando el id no es valido", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) })
        public ResponseEntity<UEstudianteResp> getById(@PathVariable String id) {
                return ResponseEntity.ok(this.estudianteService.getById(id));
        }
}
