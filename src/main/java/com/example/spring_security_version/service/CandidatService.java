package com.example.spring_security_version.service;

import com.example.spring_security_version.entity.Candidat;
import com.example.spring_security_version.exception.CandidatNotFoundException;
import com.example.spring_security_version.repository.CandidatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidatService {

    @Autowired
    CandidatRepository candidatRepository;

    public Candidat save(Candidat candidat) {

        // TODO Auto-generated method stub

        return candidatRepository.save(candidat);

    }

    public List<Candidat> findAll() {

        // TODO Auto-generated method stub

        return candidatRepository.findAll();
    }

    public Optional<Candidat> findById(long id) throws CandidatNotFoundException {

        // TODO Auto-generated method stub

        return candidatRepository.findById(id);
    }

    public Candidat updateCandidat(long id, Candidat candidatDetails) throws CandidatNotFoundException {
        Candidat candidat=candidatRepository.findById(id)
                .orElseThrow(()-> new CandidatNotFoundException(id));
        candidat.setId(id);
        candidat.setNom(candidatDetails.getNom());
        candidat.setPrenom(candidatDetails.getPrenom());
        candidat.setEmail(candidatDetails.getEmail());
        candidat.setLogin(candidatDetails.getLogin());
        candidat.setPassword(candidatDetails.getPassword());
        candidat.setCin(candidatDetails.getCin());
        candidat.setTelephone(candidatDetails.getTelephone());
        candidat.setAdresse(candidatDetails.getAdresse());
        candidat.setCode_pin(candidatDetails.getCode_pin());
        candidat.setCode_badge(candidatDetails.getCode_badge());
        candidat.setPoste(candidatDetails.getPoste());
        candidat.setDate_Embauche(candidatDetails.getDate_Embauche());
        candidat.setDate_Naissance(candidatDetails.getDate_Naissance());
        candidat.setRole(candidatDetails.getRole());
        candidat.setStatus(candidatDetails.getStatus());
        candidat.setCv(candidatDetails.getCv());
        candidat.setLettre_motivation(candidatDetails.getLettre_motivation());
        /*candidat.setConfirmPassword(candidatDetails.getConfirmPassword());

        candidat.setSexs(candidatDetails.getSexs());*/

        return candidatRepository.save(candidat);
    }

    public void deleteCandidat(long id) throws CandidatNotFoundException {

        Candidat candidat=candidatRepository.findById(id)
                .orElseThrow(()-> new CandidatNotFoundException(id));

        candidatRepository.delete(candidat);

    }

}
