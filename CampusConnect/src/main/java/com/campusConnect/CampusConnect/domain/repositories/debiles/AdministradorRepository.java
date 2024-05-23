package com.campusConnect.CampusConnect.domain.repositories.debiles;

// import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.campusConnect.CampusConnect.domain.entities.debiles.Administrador;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Long> {
    
    // @Query(value = "SELECT  a FROM administrador a WHERE a.descripcionCargo :descrip")
    // public List<Administrador> findByDescripcion(String descrip);
}
