package com.campusConnect.CampusConnect.domain.entities.debiles;

import com.campusConnect.CampusConnect.domain.entities.intermedias.Clase;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "actividad")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Actividad {
    /* Atributos */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idActividad;

    @Column(nullable = false)
    private Float nota;

    @Column(nullable = false)
    private String descripcion;

    /* Relación con tabla Clase */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clase_id", referencedColumnName = "idClase")
    private Clase clase;

    /* Relación con tabla Estudiante */
    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "estudiante_id", referencedColumnName = "idEstudiante")
    // private Estudiante estudiante;
}
