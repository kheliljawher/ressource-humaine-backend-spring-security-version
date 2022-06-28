package com.example.spring_security_version.service;

import com.example.spring_security_version.entity.Utilisateur;
import com.example.spring_security_version.exception.UtilisateurNotFoundException;
import com.example.spring_security_version.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {

    @Autowired
    UtilisateurRepository utilisateurRepository;

    public Utilisateur save(Utilisateur utilisateur) {

        // TODO Auto-generated method stub

        return utilisateurRepository.save(utilisateur);

    }

    public List<Utilisateur> findAll() {

        // TODO Auto-generated method stub

        return utilisateurRepository.findAll();
    }

    public Optional<Utilisateur> findById(long id) throws UtilisateurNotFoundException {

        // TODO Auto-generated method stub

        return utilisateurRepository.findById(id);
    }

    public Utilisateur updateUtilisateur(long id, Utilisateur utilisateurDetails) throws UtilisateurNotFoundException {
        Utilisateur utilisateur=utilisateurRepository.findById(id)
                .orElseThrow(()-> new UtilisateurNotFoundException(id));
        utilisateur.setId(id);
        utilisateur.setNom(utilisateurDetails.getNom());
        utilisateur.setPrenom(utilisateurDetails.getPrenom());
        utilisateur.setEmail(utilisateurDetails.getEmail());
        utilisateur.setLogin(utilisateurDetails.getLogin());
        utilisateur.setPassword(utilisateurDetails.getPassword());
        utilisateur.setCin(utilisateurDetails.getCin());
        utilisateur.setTelephone(utilisateurDetails.getTelephone());
        utilisateur.setAdresse(utilisateurDetails.getAdresse());
        utilisateur.setCode_pin(utilisateurDetails.getCode_pin());
        utilisateur.setCode_badge(utilisateurDetails.getCode_badge());
        utilisateur.setPoste(utilisateurDetails.getPoste());
        utilisateur.setDate_Embauche(utilisateurDetails.getDate_Embauche());
        utilisateur.setDate_Naissance(utilisateurDetails.getDate_Naissance());
        utilisateur.setRole(utilisateurDetails.getRole());
        /*utilisateur.setConfirmPassword(utilisateurDetails.getConfirmPassword());
        utilisateur.setStatus(utilisateurDetails.getStatus());
        utilisateur.setSexs(utilisateurDetails.getSexs());*/

        return utilisateurRepository.save(utilisateur);
    }

    public void deleteUtilisateur(long id) throws UtilisateurNotFoundException {

        Utilisateur utilisateur=utilisateurRepository.findById(id)
                .orElseThrow(()-> new UtilisateurNotFoundException(id));

        utilisateurRepository.delete(utilisateur);

    }



}
