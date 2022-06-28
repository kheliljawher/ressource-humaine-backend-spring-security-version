package com.example.spring_security_version.controller;

import com.example.spring_security_version.entity.Candidature;
import com.example.spring_security_version.entity.Departement;
import com.example.spring_security_version.exception.CandidatureNotFoundException;
import com.example.spring_security_version.repository.DepartementRepository;
import com.example.spring_security_version.service.CandidatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@RestController
@RequestMapping("/api/candidature/")
@EnableSwagger2
@CrossOrigin(origins = "*")
public class CandidatureController {

    @Autowired
    private CandidatureService candidatureService;

    @Autowired
    private DepartementRepository departementRepository;

    @GetMapping("/")

    public String getMessage() {
        return "Candidature controller ...";
    }

    @GetMapping("getAll")
    public List<Candidature> getAllCandidatures(){

        return candidatureService.findAll();

    }

    @GetMapping("{id}")
    public ResponseEntity<Candidature> getCandidatureById(@PathVariable(value = "id") Long id)
            throws CandidatureNotFoundException {

        Candidature candidature =
                candidatureService
                        .findById(id)
                        .orElseThrow(() -> new CandidatureNotFoundException(id));
        return ResponseEntity.ok().body(candidature);

    }

    @PostMapping("create/{id_departement}")
    @ResponseStatus(HttpStatus.CREATED)
    //@ResponseHeader(description = "abc")
    public ResponseEntity<HttpStatus> createCandidature(@Validated @RequestBody Candidature candidature, @PathVariable Long id_departement){

        try {
            Departement departement=departementRepository.findById(id_departement).get();
            candidature.setDepartement(departement);
            candidatureService.save(candidature);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("{id_candidature}/{id_departement}")
    public ResponseEntity<HttpStatus> updateCandidature(

            @PathVariable(value = "id") Long id, @PathVariable Long id_departement, @Validated @RequestBody Candidature candidatureDetails) throws CandidatureNotFoundException
    {
        Departement departement=departementRepository.findById(id_departement).get();
            candidatureDetails.setDepartement(departement);
        candidatureService.updateCandidature(id,candidatureDetails );
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus>  deleteCandidature(@PathVariable(value = "id") Long id){

        try {
            candidatureService.deleteCandidature(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
