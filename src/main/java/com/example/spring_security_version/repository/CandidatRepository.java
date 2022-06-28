package com.example.spring_security_version.repository;

import com.example.spring_security_version.entity.Candidat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidatRepository extends JpaRepository<Candidat,Long> {
}
