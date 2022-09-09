package com.example.spring_security_version.service;

import com.example.spring_security_version.entity.Conge;
import com.example.spring_security_version.exception.CongeNotFoundException;
import com.example.spring_security_version.repository.CongeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CongeService {

    @Autowired
    CongeRepository congeRepository;

    public Conge save(Conge conge) {

        // TODO Auto-generated method stub

        return congeRepository.save(conge);

    }

    public List<Conge> findAll() {

        // TODO Auto-generated method stub

        return congeRepository.findAll();
    }

    public Optional<Conge> findById(long id) throws CongeNotFoundException {

        // TODO Auto-generated method stub

        return congeRepository.findById(id);
    }

    public Conge updateConge(long id, Conge congeDetails) throws CongeNotFoundException {
        Conge conge=congeRepository.findById(id)
                .orElseThrow(()-> new CongeNotFoundException(id));
        conge.setId(id);
        conge.setType_conge(congeDetails.getType_conge());
        conge.setDate_debut(congeDetails.getDate_debut());
        conge.setDate_fin(congeDetails.getDate_fin());
        conge.setUtilisateur(congeDetails.getUtilisateur());
       /* conge.setNbr_jour(congeDetails.getNbr_jour());
        conge.setRaison(congeDetails.getRaison());
        conge.setStatus(congeDetails.getStatus());
        conge.setDescription(congeDetails.getDescription());*/

        return congeRepository.save(conge);
    }

    public void deleteConge(long id) throws CongeNotFoundException {

        Conge conge=congeRepository.findById(id)
                .orElseThrow(()-> new CongeNotFoundException(id));

        congeRepository.delete(conge);

    }

    public List<Conge> findByType(Long id_utilisateur, String type) {
        return congeRepository.findByUtilisateurAndType_conge(id_utilisateur,type);
    }
}
