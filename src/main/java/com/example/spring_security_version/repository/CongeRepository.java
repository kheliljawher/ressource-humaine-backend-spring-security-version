package com.example.spring_security_version.repository;

import com.example.spring_security_version.entity.Conge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CongeRepository extends JpaRepository<Conge,Long> {

    @Query("SELECT u FROM Conge u WHERE u.utilisateur.id=?1 AND u.type_conge=?2 ")
    List<Conge> findByUtilisateurAndType_conge(Long utilisateur, String type_conge);
}
