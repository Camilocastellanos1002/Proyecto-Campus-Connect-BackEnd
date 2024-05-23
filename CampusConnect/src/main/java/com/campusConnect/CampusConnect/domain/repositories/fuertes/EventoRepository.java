package com.campusConnect.CampusConnect.domain.repositories.fuertes;

// import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.campusConnect.CampusConnect.domain.entities.fuertes.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {
    /**
     * JPQL trabaja con las entidades de jpa por lo tanto los selects deber ser sobre ellas, 
     * ademas podemos recibir variables a travez del metodo
    
    //@Query(value = "select s from ´'entidad' s where s.'atributo'")
    //public List<Evento> selectEventos();

    //minuto 2:00:00

    @Query(value = "SELECT  e FROM evento e WHERE e.titulo :title")
    public List<Evento> findByTitulo(String title);*/
    
}
