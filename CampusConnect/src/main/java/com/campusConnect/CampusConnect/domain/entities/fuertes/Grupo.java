package com.campusConnect.CampusConnect.domain.entities.fuertes;

import java.util.List;

import com.campusConnect.CampusConnect.domain.entities.debiles.Estudiante;
import com.campusConnect.CampusConnect.domain.entities.debiles.Profesor;
import com.campusConnect.CampusConnect.domain.entities.intermedias.Agenda;
import com.campusConnect.CampusConnect.domain.entities.intermedias.Clase;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "grupo")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Grupo {
    /* Atributos */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGrupo;

    @Column(nullable = false)
    private String nombre;

    /* Relacion con tabla Profesor */
    @OneToOne(mappedBy = "grupo")
    private Profesor profesor;

    /* Relación con tabla Estudiante */
    @OneToMany(mappedBy = "grupo", cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.EAGER)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Estudiante> estudiantes;

    /* Relación con tabla Agenda */
    @OneToMany(mappedBy = "grupo", cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.EAGER)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Agenda> agendas;

    /* Relación con tabla Clase */
    @OneToMany(mappedBy = "grupo", cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.EAGER)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Clase> clases;
}
