package com.campusConnect.CampusConnect.domain.entities.intermedias;

import java.sql.Date;
import java.sql.Time;

import com.campusConnect.CampusConnect.domain.entities.fuertes.Evento;
import com.campusConnect.CampusConnect.domain.entities.fuertes.Grupo;

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

@Entity(name = "agenda")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Agenda {
    /* Atributos */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAgenda;

    @Column(nullable = false)
    private String eventTitle;

    @Column(nullable = false)
    private Date eventStartDate;

    @Column(nullable = false)
    private Date eventEndDate;

    @Column(nullable = false)
    private Time eventStartTime;

    @Column(nullable = false)
    private Time eventEndTime;
    
    @Column(nullable = false)
    private String eventLocation;

    /* Relación con tabla Evento */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "evento_id", referencedColumnName = "idEvento")
    private Evento evento;

    /* Relación con tabla Grupo */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grupo_id", referencedColumnName = "idGrupo")
    private Grupo grupo;
}
