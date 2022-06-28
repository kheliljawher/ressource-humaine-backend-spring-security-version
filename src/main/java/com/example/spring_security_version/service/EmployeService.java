package com.example.spring_security_version.service;

import com.example.spring_security_version.entity.Employe;
import com.example.spring_security_version.exception.EmployeNotFoundException;
import com.example.spring_security_version.repository.EmployeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeService {

    @Autowired
    EmployeRepository employeRepository;

    public Employe save(Employe employe) {

        // TODO Auto-generated method stub

        return employeRepository.save(employe);

    }

    public List<Employe> findAll() {

        // TODO Auto-generated method stub

        return employeRepository.getAllEmpActif("OUI");
    }

    public Optional<Employe> findById(long id) throws EmployeNotFoundException {

        // TODO Auto-generated method stub

        return employeRepository.findById(id);
    }

    /*public Optional<Employe> findByNom(String nom) throws EmployeNotFoundException {

        // TODO Auto-generated method stub

        return employeRepository.findByNom(nom);
    }

    public Optional<Employe> findByPrenom(String prenom) throws EmployeNotFoundException {

        // TODO Auto-generated method stub

        return employeRepository.findByPrenom(prenom);
    }*/


    public Employe updateEmploye(long id, Employe employeDetails) throws EmployeNotFoundException {
        Employe employe=employeRepository.findById(id)
                .orElseThrow(()-> new EmployeNotFoundException(id));
        employe.setId(id);
        employe.setNom(employeDetails.getNom()==null ? employe.getNom() : employeDetails.getNom());
        employe.setPrenom(employeDetails.getPrenom()==null ? employe.getPrenom() : employeDetails.getPrenom());
        employe.setEmail(employeDetails.getEmail()==null ? employe.getEmail() : employeDetails.getEmail());
        employe.setLogin(employeDetails.getLogin()==null ? employe.getLogin() : employeDetails.getLogin());
        employe.setPassword(employeDetails.getPassword()==null ? employe.getPassword() : employeDetails.getPassword());
        employe.setCin(employeDetails.getCin()==null ? employe.getCin() : employeDetails.getCin());
        employe.setTelephone(employeDetails.getTelephone()==null ? employe.getTelephone() : employeDetails.getTelephone());
        employe.setAdresse(employeDetails.getAdresse()==null ? employe.getAdresse() : employeDetails.getAdresse());
        employe.setCode_pin(employeDetails.getCode_pin()==null ? employe.getCode_pin() : employeDetails.getCode_pin());
        employe.setCode_badge(employeDetails.getCode_badge()==null ? employe.getCode_badge() : employeDetails.getCode_badge());
        employe.setPoste(employeDetails.getPoste()==null ? employe.getPoste() : employeDetails.getPoste());
        employe.setDate_Embauche(employeDetails.getDate_Embauche()==null ? employe.getDate_Naissance() : employeDetails.getDate_Embauche());
        employe.setDate_Naissance(employeDetails.getDate_Naissance()==null ? employe.getDate_Naissance() : employeDetails.getDate_Naissance());
        employe.setRole(employeDetails.getRole()==null ? employe.getRole() : employeDetails.getRole());
        employe.setImage(employeDetails.getImage()==null ? employe.getImage() : employeDetails.getImage());
        employe.setConfirmPassword(employeDetails.getConfirmPassword()==null ? employe.getConfirmPassword() : employeDetails.getConfirmPassword());
        employe.setStatus(employeDetails.getStatus()==null ? employe.getStatus() : employeDetails.getStatus());
        employe.setSexs(employeDetails.getSexs()==null ? employe.getSexs() : employeDetails.getSexs());
        employe.setPlanning(employeDetails.getPlanning());
        employe.setDepartement(employeDetails.getDepartement());


        return employeRepository.save(employe);
    }

    public void deleteEmploye(long id) throws EmployeNotFoundException {

        Employe employe=employeRepository.findById(id)
                .orElseThrow(()-> new EmployeNotFoundException(id));

        employeRepository.delete(employe);

    }

}
