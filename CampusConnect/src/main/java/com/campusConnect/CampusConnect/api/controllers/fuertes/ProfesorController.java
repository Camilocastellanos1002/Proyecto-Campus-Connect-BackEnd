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

import com.campusConnect.CampusConnect.api.dto.request.fuertes.UProfesorReq;
import com.campusConnect.CampusConnect.api.dto.response.fuertes.UProfesorResp;
import com.campusConnect.CampusConnect.infrastructure.abstract_services.fuertes.IProfesorService;
import com.campusConnect.CampusConnect.util.enums.SortType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

@RestController
@RequestMapping("/profesor")
@Data
@AllArgsConstructor
public class ProfesorController {
        /* Inyección de dependencias*/
        @Autowired
        private final IProfesorService profesorService;

        @GetMapping
        @Operation(summary = "Obtiene los usuarios de tipo profesor de la plataforma de forma páginada y organizada por nombre")
        public ResponseEntity<Page<UProfesorResp>> getAll(
                @RequestParam(defaultValue = "1") int page,
                @RequestParam(defaultValue = "5") int size,
                @RequestHeader(required = false) SortType sortType) {
                if (Objects.isNull(sortType)) {
                        sortType = SortType.NONE;
                }
                
                return ResponseEntity.ok(this.profesorService.getAll(page - 1, size, sortType));
        }

        @PostMapping
        @Operation(summary = "Crea un profesor")
        @ApiResponse(responseCode = "400", description = "Cuando el id no es valido", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
        public ResponseEntity<UProfesorResp> create(
                @Validated UProfesorReq request) {
                return ResponseEntity.ok(this.profesorService.create(request));
        }

        @PutMapping(path = "/{id}")
        @Operation(summary = "Actualiza un profesor por id")
        @ApiResponse(responseCode = "400", description = "Cuando el id no es valido", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
        public ResponseEntity<UProfesorResp> update(
                @PathVariable String id, @Validated @RequestBody UProfesorReq request) {
                return ResponseEntity.ok(this.profesorService.update(request, id));
        }

        @DeleteMapping(path = "/{id}")
        @Operation(summary = "Elimina un profesor por id")
        @ApiResponse(responseCode = "400", description = "Cuando el id no es valido", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
        public ResponseEntity<Void> delete(@PathVariable String id) {
                this.profesorService.delete(id);

                return ResponseEntity.noContent().build();
        }

        @GetMapping(path = "/{id}")
        @Operation(summary = "Obtiene un profesor por id")
        @ApiResponse(responseCode = "400", description = "Cuando el id no es valido", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
        public ResponseEntity<UProfesorResp> getById(@PathVariable String id) {
                return ResponseEntity.ok(this.profesorService.getById(id));
        }
}
