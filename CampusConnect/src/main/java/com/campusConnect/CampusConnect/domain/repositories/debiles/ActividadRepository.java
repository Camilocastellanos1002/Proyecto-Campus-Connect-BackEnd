package com.campusConnect.CampusConnect.domain.repositories.debiles;

// import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.campusConnect.CampusConnect.domain.entities.debiles.Actividad;

@Repository
public interface ActividadRepository extends JpaRepository<Actividad, Long> {
    
    // @Query(value = "SELECT  a FROM actividad a WHERE a.nota :nota")
    // public List<Actividad> findByNota(Float nota);

    // @Query(value = "SELECT  a FROM actividad a WHERE a.descripcion :descrip")
    // public List<Actividad> findByDescripcion(String descrip);

}
