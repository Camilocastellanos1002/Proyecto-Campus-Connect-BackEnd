package com.campusConnect.CampusConnect.domain.repositories.intermedias;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.campusConnect.CampusConnect.domain.entities.intermedias.Clase;

@Repository
public interface ClaseRepository extends JpaRepository<Clase, Long> {
    
}
