package com.example.spring_security_version.service;

import com.example.spring_security_version.entity.Candidature;
import com.example.spring_security_version.exception.CandidatureNotFoundException;
import com.example.spring_security_version.repository.CandidatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidatureService {

    @Autowired
    CandidatureRepository candidatureRepository;

    public Candidature save(Candidature candidature) {

        // TODO Auto-generated method stub

        return candidatureRepository.save(candidature);

    }

    public List<Candidature> findAll() {

        // TODO Auto-generated method stub

        return candidatureRepository.findAll();
    }

    public Optional<Candidature> findById(long id) throws CandidatureNotFoundException {

        // TODO Auto-generated method stub

        return candidatureRepository.findById(id);
    }

    public Candidature updateCandidature(long id, Candidature candidatureDetails) throws CandidatureNotFoundException {
        Candidature candidature=candidatureRepository.findById(id)
                .orElseThrow(()-> new CandidatureNotFoundException(id));
        candidature.setId(id);
        candidature.setOffre(candidatureDetails.getOffre());
        candidature.setTitre(candidatureDetails.getTitre());
        candidature.setDate_debut(candidatureDetails.getDate_debut());
        candidature.setDate_fin(candidatureDetails.getDate_fin());
        candidature.setStatus(candidatureDetails.getStatus());
        candidature.setApplicant(candidatureDetails.getApplicant());
        candidature.setType(candidatureDetails.getType());
        candidature.setExperience(candidatureDetails.getExperience());
        candidature.setSalaire(candidatureDetails.getSalaire());
        candidature.setDescription(candidatureDetails.getDescription());

        return candidatureRepository.save(candidature);
    }

    public void deleteCandidature(long id) throws CandidatureNotFoundException {

        Candidature candidature=candidatureRepository.findById(id)
                .orElseThrow(()-> new CandidatureNotFoundException(id));

        candidatureRepository.delete(candidature);

    }

}
