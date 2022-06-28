package com.example.spring_security_version.service;

import com.example.spring_security_version.entity.DemandeCandidature;
import com.example.spring_security_version.exception.DemandeCandidatureNotFoundException;
import com.example.spring_security_version.repository.DemandeCandidatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DemandeCandidatureService {

    @Autowired
    DemandeCandidatureRepository demandeCandidatureRepository;

    public DemandeCandidature save(DemandeCandidature demandeCandidature) {

        // TODO Auto-generated method stub

        return demandeCandidatureRepository.save(demandeCandidature);

    }

    public List<DemandeCandidature> findAll() {

        // TODO Auto-generated method stub

        return demandeCandidatureRepository.findAll();
    }

    public Optional<DemandeCandidature> findById(long id) throws DemandeCandidatureNotFoundException {

        // TODO Auto-generated method stub

        return demandeCandidatureRepository.findById(id);
    }

    public DemandeCandidature updateDemandeCandidature(long id, DemandeCandidature demandeCandidatureDetails) throws DemandeCandidatureNotFoundException {
        DemandeCandidature demandeCandidature=demandeCandidatureRepository.findById(id)
                .orElseThrow(()-> new DemandeCandidatureNotFoundException(id));
        demandeCandidature.setId(id);
        demandeCandidature.setEtat(demandeCandidatureDetails.getEtat());


        return demandeCandidatureRepository.save(demandeCandidature);
    }

    public void deleteDemandeCandidature(long id) throws DemandeCandidatureNotFoundException {

        DemandeCandidature demandeCandidature=demandeCandidatureRepository.findById(id)
                .orElseThrow(()-> new DemandeCandidatureNotFoundException(id));

        demandeCandidatureRepository.delete(demandeCandidature);

    }

}
