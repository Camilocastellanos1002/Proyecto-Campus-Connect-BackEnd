package com.campusConnect.CampusConnect.domain.repositories.intermedias;

// import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.campusConnect.CampusConnect.domain.entities.intermedias.Asistencia;

@Repository
public interface AsistenciaRepository extends JpaRepository<Asistencia, Long> {
    
    // @Query(value = "SELECT a FROM asistencia a where a.asistencia")
    // public List<EstadoAsistencia> selectByEstadoAsistencia(EstadoAsistencia asis);
}
