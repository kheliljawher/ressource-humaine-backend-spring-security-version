package com.example.spring_security_version.controller;

import com.example.spring_security_version.entity.ChefDepartement;
import com.example.spring_security_version.entity.Conge;
import com.example.spring_security_version.entity.Employe;
import com.example.spring_security_version.entity.Utilisateur;
import com.example.spring_security_version.exception.CongeNotFoundException;
import com.example.spring_security_version.repository.ChefDepartementRepository;
import com.example.spring_security_version.repository.EmployeRepository;
import com.example.spring_security_version.repository.UtilisateurRepository;
import com.example.spring_security_version.service.CongeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@RestController
@RequestMapping("/api/conge/")
@EnableSwagger2
@CrossOrigin(origins = "*")
public class CongeController {

    @Autowired
    private CongeService congeService;

    @Autowired
    private EmployeRepository employeRepository;

    @Autowired
    private ChefDepartementRepository chefDepartementRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @GetMapping("/")

    public String getMessage() {
        return "Conge controller ...";
    }

    @GetMapping("getAll")
    public List<Conge> getAllConges(){

        return congeService.findAll();

    }

    @GetMapping("{id}")
    public ResponseEntity<Conge> getCongeById(@PathVariable(value = "id") Long id)
            throws CongeNotFoundException {

        Conge conge =
                congeService
                        .findById(id)
                        .orElseThrow(() -> new CongeNotFoundException(id));
        return ResponseEntity.ok().body(conge);

    }

    @PostMapping("create/{id_utilisateur}")
    @ResponseStatus(HttpStatus.CREATED)
    //@ResponseHeader(description = "abc")
    public ResponseEntity<HttpStatus> createConge(@Validated @RequestBody Conge conge, @PathVariable Long id_utilisateur){

        try {
            Utilisateur utilisateur= utilisateurRepository.findById(id_utilisateur).get();
            conge.setStatus("NOUVEAU");
            conge.setUtilisateur(utilisateur);
            System.out.println(utilisateur);
            /*ChefDepartement chefDepartement=chefDepartementRepository.findById(id_chefDepartement).get();
            conge.setChef_departement(chefDepartement);
            System.out.println(chefDepartement);*/
            congeService.save(conge);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("{id_conge}/{id_utilisateur}")
    public ResponseEntity<HttpStatus> updateConge(

            @PathVariable(value = "id_conge") Long id_conge, @PathVariable Long id_utilisateur, @Validated @RequestBody Conge congeDetails) throws CongeNotFoundException
    {
        Utilisateur utilisateur= utilisateurRepository.findById(id_utilisateur).get();
        congeDetails.setUtilisateur(utilisateur);
        System.out.println(utilisateur);
        congeService.updateConge(id_conge,congeDetails);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus>  deleteConge(@PathVariable(value = "id") Long id){

        try {
            congeService.deleteConge(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/findByType/{id_utilisateur}/{type}")
    public List<Conge> getAllCongeByType(@PathVariable Long id_utilisateur,@PathVariable String type){
        return congeService.findByType(id_utilisateur,type);
    }

}
