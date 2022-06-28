package com.example.spring_security_version.service;

import com.example.spring_security_version.entity.ResponsableRH;
import com.example.spring_security_version.exception.ResponsableRHNotFoundException;
import com.example.spring_security_version.repository.ResponsableRHRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResponsableRHService {

    @Autowired
    ResponsableRHRepository responsableRHRepository;

    public ResponsableRH save(ResponsableRH responsableRH) {

        // TODO Auto-generated method stub

        return responsableRHRepository.save(responsableRH);

    }

    public List<ResponsableRH> findAll() {

        // TODO Auto-generated method stub

        return responsableRHRepository.findAll();
    }

    public Optional<ResponsableRH> findById(long id) throws ResponsableRHNotFoundException {

        // TODO Auto-generated method stub

        return responsableRHRepository.findById(id);
    }

    public ResponsableRH updateResponsableRH(long id, ResponsableRH responsableRHDetails) throws ResponsableRHNotFoundException {
        ResponsableRH responsableRH=responsableRHRepository.findById(id)
                .orElseThrow(()-> new ResponsableRHNotFoundException(id));
        responsableRH.setId(id);
        responsableRH.setNom(responsableRHDetails.getNom());
        responsableRH.setPrenom(responsableRHDetails.getPrenom());
        responsableRH.setEmail(responsableRHDetails.getEmail());
        responsableRH.setLogin(responsableRHDetails.getLogin());
        responsableRH.setPassword(responsableRHDetails.getPassword());
        responsableRH.setCin(responsableRHDetails.getCin());
        responsableRH.setTelephone(responsableRHDetails.getTelephone());
        responsableRH.setAdresse(responsableRHDetails.getAdresse());
        responsableRH.setCode_pin(responsableRHDetails.getCode_pin());
        responsableRH.setCode_badge(responsableRHDetails.getCode_badge());
        responsableRH.setPoste(responsableRHDetails.getPoste());
        responsableRH.setDate_Embauche(responsableRHDetails.getDate_Embauche());
        responsableRH.setDate_Naissance(responsableRHDetails.getDate_Naissance());
        responsableRH.setRole(responsableRHDetails.getRole());
       /* responsableRH.setConfirmPassword(responsableRHDetails.getConfirmPassword());
        responsableRH.setStatus(responsableRHDetails.getStatus());
        responsableRH.setSexs(responsableRHDetails.getSexs());*/

        return responsableRHRepository.save(responsableRH);
    }

    public void deleteResponsableRH(long id) throws ResponsableRHNotFoundException {

        ResponsableRH responsableRH=responsableRHRepository.findById(id)
                .orElseThrow(()-> new ResponsableRHNotFoundException(id));

        responsableRHRepository.delete(responsableRH);

    }

}
