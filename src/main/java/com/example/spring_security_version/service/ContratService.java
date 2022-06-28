package com.example.spring_security_version.service;

import com.example.spring_security_version.entity.Contrat;
import com.example.spring_security_version.exception.ContratNotFoundException;
import com.example.spring_security_version.repository.ContratRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContratService {

    @Autowired
    ContratRepository contratRepository;

    public Contrat save(Contrat contrat) {

        // TODO Auto-generated method stub

        return contratRepository.save(contrat);

    }

    public List<Contrat> findAll() {

        // TODO Auto-generated method stub

        return contratRepository.findAll();
    }

    public Optional<Contrat> findById(long id) throws ContratNotFoundException {

        // TODO Auto-generated method stub

        return contratRepository.findById(id);
    }

    public Contrat updateContrat(long id, Contrat contratDetails) throws ContratNotFoundException {
        Contrat contrat=contratRepository.findById(id)
                .orElseThrow(()-> new ContratNotFoundException(id));
        contrat.setId(id);
        contrat.setType_contrat(contratDetails.getType_contrat());
        contrat.setDate_debut(contratDetails.getDate_debut());
        contrat.setDate_fin(contratDetails.getDate_fin());
        contrat.setSalaire(contratDetails.getSalaire());
        contrat.setUtilisateur(contratDetails.getUtilisateur());
       /* contrat.setDescription(contratDetails.getDescription());*/

        return contratRepository.save(contrat);
    }

    public void deleteContrat(long id) throws ContratNotFoundException {

        Contrat contrat=contratRepository.findById(id)
                .orElseThrow(()-> new ContratNotFoundException(id));

        contratRepository.delete(contrat);

    }

}
