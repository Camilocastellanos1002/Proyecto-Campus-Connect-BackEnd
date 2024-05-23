package com.campusConnect.CampusConnect.domain.repositories.debiles;

// import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.campusConnect.CampusConnect.domain.entities.debiles.SeguimientoPsicologico;
// import com.campusConnect.CampusConnect.util.enums.EstadoAnimo;

@Repository
public interface SeguimientoPsicologicoRepository extends JpaRepository<SeguimientoPsicologico, Long> {
    
    // @Query(value = "SELECT  s FROM seguimiento_psicologico s WHERE s.estadoAnimo :estado")
    // public List<EstadoAnimo> findByEstado(EstadoAnimo estado);
}
