package com.campusConnect.CampusConnect.domain.repositories.debiles;

// import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.campusConnect.CampusConnect.domain.entities.debiles.Estudiante;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
    
    // @Query(value = "SELECT  e FROM estudiante e WHERE e.acudientes :acu")
    // public List<Estudiante> findByAcudiente(String acu);
}
