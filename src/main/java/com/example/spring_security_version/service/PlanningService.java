package com.example.spring_security_version.service;

import com.example.spring_security_version.entity.Employe;
import com.example.spring_security_version.entity.Planning;
import com.example.spring_security_version.exception.PlanningNotFoundException;
import com.example.spring_security_version.repository.PlanningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanningService {

    @Autowired
    PlanningRepository planningRepository;

    public Planning save(Planning planning) {

        // TODO Auto-generated method stub

        return planningRepository.save(planning);

    }

    public Planning findPlanningById(Long id_planning) {

        // TODO Auto-generated method stub

        return planningRepository.findById(id_planning).get();

    }

    public List<Planning> findAll() {

        // TODO Auto-generated method stub

        return planningRepository.findAll();
    }

    public Optional<Planning> findById(long id) throws PlanningNotFoundException {

        // TODO Auto-generated method stub

        return planningRepository.findById(id);
    }

    public Planning updatePlanning(long id, Planning planningDetails) throws PlanningNotFoundException {
        Planning planning=planningRepository.findById(id)
                .orElseThrow(()-> new PlanningNotFoundException(id));
        planning.setId(id);
        planning.setNom(planningDetails.getNom());
        planning.setDate_debut(planningDetails.getDate_debut());
        planning.setDate_fin(planningDetails.getDate_fin());
        //planning.setDescription(planningDetails.getDescription());

        return planningRepository.save(planning);
    }

    public void deletePlanning(long id) throws PlanningNotFoundException {

        Planning planning=planningRepository.findById(id)
                .orElseThrow(()-> new PlanningNotFoundException(id));

        planningRepository.delete(planning);

    }

}
