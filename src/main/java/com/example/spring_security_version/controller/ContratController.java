package com.example.spring_security_version.controller;

import com.example.spring_security_version.entity.Contrat;
import com.example.spring_security_version.entity.Employe;
import com.example.spring_security_version.entity.Utilisateur;
import com.example.spring_security_version.exception.ContratNotFoundException;
import com.example.spring_security_version.repository.EmployeRepository;
import com.example.spring_security_version.repository.UtilisateurRepository;
import com.example.spring_security_version.service.ContratService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@RestController
@RequestMapping("/api/contrat/")
@EnableSwagger2
@CrossOrigin(origins = "*")
public class ContratController {

    @Autowired
    private ContratService contratService;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @GetMapping("/")

    public String getMessage() {
        return "Contrat controller ...";
    }

    @GetMapping("getAll")
    public List<Contrat> getAllContrats(){

        return contratService.findAll();

    }

    @GetMapping("{id}")
    public ResponseEntity<Contrat> getContratById(@PathVariable(value = "id") Long id)
            throws ContratNotFoundException {

        Contrat contrat =
                contratService
                        .findById(id)
                        .orElseThrow(() -> new ContratNotFoundException(id));
        return ResponseEntity.ok().body(contrat);

    }

    @PostMapping("create/{id_utilisateur}")
    @ResponseStatus(HttpStatus.CREATED)
    //@ResponseHeader(description = "abc")
    public ResponseEntity<HttpStatus> createContrat(@Validated @RequestBody Contrat contrat, @PathVariable Long id_utilisateur){

        try {
            Utilisateur utilisateur= utilisateurRepository.findById(id_utilisateur).get();
            contrat.setUtilisateur(utilisateur);
            System.out.println(utilisateur);
            contratService.save(contrat);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("{id_contrat}/{id_utilisateur}")
    public ResponseEntity<HttpStatus> updateContrat(

            @PathVariable(value = "id_contrat") Long id_contrat, @Validated @RequestBody Contrat contratDetails, @PathVariable Long id_utilisateur) throws ContratNotFoundException
    {
        Utilisateur utilisateur= utilisateurRepository.findById(id_utilisateur).get();
        contratDetails.setUtilisateur(utilisateur);
        System.out.println(utilisateur);
        contratService.updateContrat(id_contrat,contratDetails );
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus>  deleteContrat(@PathVariable(value = "id") Long id){

        try {
            contratService.deleteContrat(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
