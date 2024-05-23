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

import com.campusConnect.CampusConnect.api.dto.request.fuertes.EventoAgendaReq;
import com.campusConnect.CampusConnect.api.dto.response.fuertes.EventoAgendaResp;
import com.campusConnect.CampusConnect.infrastructure.abstract_services.fuertes.IEventoAgendaService;
import com.campusConnect.CampusConnect.util.enums.SortType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

@RestController
@RequestMapping("/evento")
@Data
@AllArgsConstructor
public class EventoController {
        /* Inyección de dependencias*/
        @Autowired
        private final IEventoAgendaService service;

        @GetMapping
        @Operation(summary = "Obtiene los eventos de forma páginada y organizada por título")
        public ResponseEntity<Page<EventoAgendaResp>> getAll(
                @RequestParam(defaultValue = "1") int page,
                @RequestParam(defaultValue = "5") int size,
                @RequestHeader(required = false) SortType sortType) {
                if (Objects.isNull(sortType)) {
                        sortType = SortType.NONE;
                }
                
                return ResponseEntity.ok(this.service.getAll(page - 1, size, sortType));
        }

        @PostMapping
        @Operation(summary = "Crea un evento")
        @ApiResponse(responseCode = "400", description = "Cuando el id no es valido", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
        public ResponseEntity<EventoAgendaResp> create(
                @Validated EventoAgendaReq request) {
                return ResponseEntity.ok(this.service.create(request));
        }

        @PutMapping(path = "/{id}")
        @Operation(summary = "Actualiza un evento por id")
        @ApiResponse(responseCode = "400", description = "Cuando el id no es valido", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
        public ResponseEntity<EventoAgendaResp> update(
                @PathVariable Long id, @Validated @RequestBody EventoAgendaReq request) {
                return ResponseEntity.ok(this.service.update(request, id));
        }
        
        @DeleteMapping(path = "/{id}")
        @Operation(summary = "Elimina un evento por id")
        @ApiResponse(responseCode = "400", description = "Cuando el id no es valido", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
        public ResponseEntity<Void> delete(@PathVariable Long id) {
                this.service.delete(id);

                return ResponseEntity.noContent().build();
        }

        @GetMapping(path = "/{id}")
        @Operation(summary = "Obtiene un evento por id")
        @ApiResponse(responseCode = "400", description = "Cuando el id no es valido", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
        public ResponseEntity<EventoAgendaResp> getById(@PathVariable Long id) {
                return ResponseEntity.ok(this.service.getById(id));
        }
}
