package com.example.spring_security_version.repository;

import com.example.spring_security_version.entity.Planning;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanningRepository extends JpaRepository<Planning,Long> {
}
