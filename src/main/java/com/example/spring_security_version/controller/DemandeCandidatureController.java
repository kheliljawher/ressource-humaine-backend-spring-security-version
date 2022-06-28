package com.example.spring_security_version.controller;

import com.example.spring_security_version.entity.Candidat;
import com.example.spring_security_version.entity.Candidature;
import com.example.spring_security_version.entity.DemandeCandidature;
import com.example.spring_security_version.entity.Planning;
import com.example.spring_security_version.exception.DemandeCandidatureNotFoundException;
import com.example.spring_security_version.repository.CandidatRepository;
import com.example.spring_security_version.repository.CandidatureRepository;
import com.example.spring_security_version.service.DemandeCandidatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@RestController
@RequestMapping("/api/demandeCandidature/")
@EnableSwagger2
@CrossOrigin(origins = "*")
public class DemandeCandidatureController {

    @Autowired
    DemandeCandidatureService demandeCandidatureService;

    @Autowired
    CandidatRepository candidatRepository;

    @Autowired
    CandidatureRepository candidatureRepository;

    @GetMapping("/")

    public String getMessage() {
        return "Demande Candidature controller ...";
    }

    @GetMapping("getAll")
    public List<DemandeCandidature> getAllDemandeCandidatures(){

        return demandeCandidatureService.findAll();

    }

    @GetMapping("{id}")
    public ResponseEntity<DemandeCandidature> getDemandeCandidatureById(@PathVariable(value = "id") Long id)
            throws DemandeCandidatureNotFoundException {

        DemandeCandidature demandeCandidature =
                demandeCandidatureService
                        .findById(id)
                        .orElseThrow(() -> new DemandeCandidatureNotFoundException(id));
        return ResponseEntity.ok().body(demandeCandidature);

    }

    @PostMapping("create/{id_candidat}/{id_candidature}")
    @ResponseStatus(HttpStatus.CREATED)
    //@ResponseHeader(description = "abc")
    public ResponseEntity<HttpStatus> createDemandeCandidature(@Validated @RequestBody DemandeCandidature demandeCandidature, @PathVariable Long id_candidat, @PathVariable Long id_candidature){

        try {
            demandeCandidature.setEtat("en attente");
            Candidat candidat=candidatRepository.findById(id_candidat).get();
            demandeCandidature.setCandidat(candidat);
            Candidature candidature=candidatureRepository.findById(id_candidature).get();
            demandeCandidature.setCandidature(candidature);
            demandeCandidatureService.save(demandeCandidature);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("{id_demande_candidature}/{id_candidat}/{id_candidature}")
    public ResponseEntity<HttpStatus> updateDemandeCandidature(

            @PathVariable(value = "id_demande_candidature") Long id_demande_candidature, @Validated @RequestBody DemandeCandidature demandeCandidatureDetails, @PathVariable Long id_candidat, @PathVariable Long id_candidature) throws DemandeCandidatureNotFoundException
    {
        //demandeCandidatureDetails.setEtat("en attente");
        Candidat candidat=candidatRepository.findById(id_candidat).get();
        demandeCandidatureDetails.setCandidat(candidat);
        Candidature candidature=candidatureRepository.findById(id_candidature).get();
        demandeCandidatureDetails.setCandidature(candidature);
        demandeCandidatureService.updateDemandeCandidature(id_demande_candidature, demandeCandidatureDetails);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus>  deleteDemandeCandidature(@PathVariable(value = "id") Long id){

        try {
            demandeCandidatureService.deleteDemandeCandidature(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
