package com.example.spring_security_version.repository;

import com.example.spring_security_version.entity.ChefDepartement;
import com.example.spring_security_version.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChefDepartementRepository extends JpaRepository<ChefDepartement,Long> {

    @Query("SELECT u FROM ChefDepartement u WHERE u.actif=?1 ")
    List<ChefDepartement> getAllChefActif(String actif);
}
