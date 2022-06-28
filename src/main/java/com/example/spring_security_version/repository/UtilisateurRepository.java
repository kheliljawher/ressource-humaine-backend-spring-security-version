package com.example.spring_security_version.repository;

import com.example.spring_security_version.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    @Query("SELECT u FROM Utilisateur u WHERE u.login=?1 and u.password = ?2")
    Utilisateur getAllUtilisateurs(String login , String password);

}
