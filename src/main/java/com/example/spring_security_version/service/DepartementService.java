package com.example.spring_security_version.service;

import com.example.spring_security_version.entity.Departement;
import com.example.spring_security_version.exception.DepartementNotFoundException;
import com.example.spring_security_version.repository.DepartementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartementService {

    @Autowired
    DepartementRepository departementRepository;

    public Departement save(Departement departement) {

        // TODO Auto-generated method stub

        return departementRepository.save(departement);

    }

    public List<Departement> findAll() {

        // TODO Auto-generated method stub

        return departementRepository.findAll();
    }

    public Optional<Departement> findById(long id) throws DepartementNotFoundException {

        // TODO Auto-generated method stub

        return departementRepository.findById(id);
    }

    public Departement updateDepartement(long id, Departement departementDetails) throws DepartementNotFoundException {
        Departement departement=departementRepository.findById(id)
                .orElseThrow(()-> new DepartementNotFoundException(id));
        departement.setId(id);
        departement.setNom(departementDetails.getNom());

        return departementRepository.save(departement);
    }

    public void deleteDepartement(long id) throws DepartementNotFoundException {

        Departement departement=departementRepository.findById(id)
                .orElseThrow(()-> new DepartementNotFoundException(id));

        departementRepository.delete(departement);

    }
}
