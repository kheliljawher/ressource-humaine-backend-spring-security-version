package com.example.spring_security_version.repository;

import com.example.spring_security_version.entity.DemandeCandidature;
import com.example.spring_security_version.entity.Employe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DemandeCandidatureRepository extends JpaRepository<DemandeCandidature ,Long> {


    @Query("SELECT u FROM DemandeCandidature u WHERE u.candidat.id =?1 ")
    List<DemandeCandidature> getDemandeByCandidatId(Long id);
}
