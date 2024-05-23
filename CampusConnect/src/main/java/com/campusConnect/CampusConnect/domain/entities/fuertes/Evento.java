package com.campusConnect.CampusConnect.domain.entities.fuertes;

import java.util.List;

import com.campusConnect.CampusConnect.domain.entities.intermedias.Agenda;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "evento")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Evento {
    /* Atributos */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEvento;

    @Column(nullable = false)
    private String titulo;

    /* Relación con tabla Agenda */
    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.EAGER)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Agenda> agendas;
}
