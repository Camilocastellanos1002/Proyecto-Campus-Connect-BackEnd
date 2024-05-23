package com.campusConnect.CampusConnect.domain.repositories.debiles;
import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.campusConnect.CampusConnect.domain.entities.debiles.Profesor;

@Repository
public interface ProfesorRepository extends JpaRepository<Profesor, Long> {

    // @Query(value = "SELECT  p FROM profesor p WHERE p.hojaVida :cv")
    // public String findByCv(String cv);
    
}
