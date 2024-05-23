package com.campusConnect.CampusConnect.domain.entities.intermedias;

import java.util.Date;

import com.campusConnect.CampusConnect.domain.entities.debiles.Estudiante;
import com.campusConnect.CampusConnect.util.enums.EstadoAsistencia;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

@Entity(name = "asistencia")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Asistencia {
    /* Atributos */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAsistencia;

    @Column(nullable = false)
    private Date dia;

    @Enumerated(EnumType.STRING)
    private EstadoAsistencia asistencia;

    /* Relación con la tabla Clase */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clase_id", referencedColumnName = "idClase")
    private Clase clase;

    /* Relación con la tabla Asistencia */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estudiante_id", referencedColumnName = "idEstudiante")
    private Estudiante estudiante;
}
