package com.example.spring_security_version.repository;

import com.example.spring_security_version.entity.Conge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CongeRepository extends JpaRepository<Conge,Long> {
}
