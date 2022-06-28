package com.example.spring_security_version.repository;

import com.example.spring_security_version.entity.ChefDepartement;
import com.example.spring_security_version.entity.Employe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeRepository extends JpaRepository<Employe,Long> {

    /*Optional<Employe> findByNom(String nom);

    Optional<Employe> findByPrenom(String prenom);*/

    @Query("SELECT u FROM Employe u WHERE u.actif=?1 ")
    List<Employe> getAllEmpActif(String actif);

}
