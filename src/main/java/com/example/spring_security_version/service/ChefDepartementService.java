package com.example.spring_security_version.service;

import com.example.spring_security_version.entity.ChefDepartement;
import com.example.spring_security_version.exception.ChefDepartementNotFoundException;
import com.example.spring_security_version.repository.ChefDepartementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChefDepartementService {

    @Autowired
    ChefDepartementRepository chefDepartementRepository;

    public ChefDepartement save(ChefDepartement chefDepartement) {

        // TODO Auto-generated method stub

        return chefDepartementRepository.save(chefDepartement);

    }

    public List<ChefDepartement> findAll() {

        // TODO Auto-generated method stub

        return chefDepartementRepository.getAllChefActif("OUI");
    }

    public Optional<ChefDepartement> findById(long id) throws ChefDepartementNotFoundException {

        // TODO Auto-generated method stub

        return chefDepartementRepository.findById(id);
    }

    public ChefDepartement updateChefDepartement(long id, ChefDepartement chefDepartementDetails) throws ChefDepartementNotFoundException {
        ChefDepartement chefDepartement=chefDepartementRepository.findById(id)
                .orElseThrow(()-> new ChefDepartementNotFoundException(id));
        chefDepartement.setId(id);
        chefDepartement.setNom(chefDepartementDetails.getNom()==null ? chefDepartement.getNom() : chefDepartementDetails.getNom());
        chefDepartement.setPrenom(chefDepartementDetails.getPrenom()==null ? chefDepartement.getPrenom() : chefDepartementDetails.getPrenom());
        chefDepartement.setEmail(chefDepartementDetails.getEmail()==null ? chefDepartement.getEmail() : chefDepartementDetails.getEmail());
        chefDepartement.setLogin(chefDepartementDetails.getLogin()==null ? chefDepartement.getLogin() : chefDepartementDetails.getLogin());
        chefDepartement.setPassword(chefDepartementDetails.getPassword()==null ? chefDepartement.getPassword() : chefDepartementDetails.getPassword());
        chefDepartement.setCin(chefDepartementDetails.getCin()==null ? chefDepartement.getCin() : chefDepartementDetails.getCin());
        chefDepartement.setTelephone(chefDepartementDetails.getTelephone()==null ? chefDepartement.getTelephone() : chefDepartementDetails.getTelephone());
        chefDepartement.setAdresse(chefDepartementDetails.getAdresse()==null ? chefDepartement.getAdresse() : chefDepartementDetails.getAdresse());
        chefDepartement.setCode_pin(chefDepartementDetails.getCode_pin()==null ? chefDepartement.getCode_pin() : chefDepartementDetails.getCode_pin());
        chefDepartement.setCode_badge(chefDepartementDetails.getCode_badge()==null ? chefDepartement.getCode_badge() : chefDepartementDetails.getCode_badge());
        chefDepartement.setPoste(chefDepartementDetails.getPoste()==null ? chefDepartement.getPoste() : chefDepartementDetails.getPoste());
        chefDepartement.setDate_Embauche(chefDepartementDetails.getDate_Embauche()==null ? chefDepartement.getDate_Naissance() : chefDepartementDetails.getDate_Embauche());
        chefDepartement.setDate_Naissance(chefDepartementDetails.getDate_Naissance()==null ? chefDepartement.getDate_Naissance() : chefDepartementDetails.getDate_Naissance());
        chefDepartement.setRole(chefDepartementDetails.getRole()==null ? chefDepartement.getRole() : chefDepartementDetails.getRole());
        chefDepartement.setImage(chefDepartementDetails.getImage()==null ? chefDepartement.getImage() : chefDepartementDetails.getImage());
        /*chefDepartement.setConfirmPassword(chefDepartementDetails.getConfirmPassword()==null ? chefDepartement.getConfirmPassword() : chefDepartementDetails.getConfirmPassword());
        chefDepartement.setStatus(chefDepartementDetails.getStatus()==null ? chefDepartement.getStatus() : chefDepartementDetails.getStatus());
        chefDepartement.setSexs(chefDepartementDetails.getSexs()==null ? chefDepartement.getSexs() : chefDepartementDetails.getSexs());*/

        return chefDepartementRepository.save(chefDepartement);
    }

    public void deleteChefDepartement(long id) throws ChefDepartementNotFoundException {

        ChefDepartement chefDepartement=chefDepartementRepository.findById(id)
                .orElseThrow(()-> new ChefDepartementNotFoundException(id));

        chefDepartementRepository.delete(chefDepartement);

    }

    public ChefDepartement findChefDepartementById(Long id_chef_departement) {
        return chefDepartementRepository.findById(id_chef_departement).get();
    }
}
