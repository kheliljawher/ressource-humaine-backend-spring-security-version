package com.example.spring_security_version.controller;

import com.example.spring_security_version.entity.Candidature;
import com.example.spring_security_version.entity.Departement;
import com.example.spring_security_version.exception.CandidatureNotFoundException;
import com.example.spring_security_version.exception.DepartementNotFoundException;
import com.example.spring_security_version.service.CandidatureService;
import com.example.spring_security_version.service.DepartementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@RestController
@RequestMapping("/api/departement/")
@EnableSwagger2
@CrossOrigin(origins = "*")
public class DepartementController {
    @Autowired
    private DepartementService departementService;

    @GetMapping("/")

    public String getMessage() {
        return "Departement controller ...";
    }

    @GetMapping("getAll")
    public List<Departement> getAllDepartements(){

        return departementService.findAll();

    }

    @GetMapping("{id}")
    public ResponseEntity<Departement> getDepartementById(@PathVariable(value = "id") Long id)
            throws DepartementNotFoundException {

        Departement departement =
                departementService
                        .findById(id)
                        .orElseThrow(() -> new DepartementNotFoundException(id));
        return ResponseEntity.ok().body(departement);

    }

    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    //@ResponseHeader(description = "abc")
    public ResponseEntity<HttpStatus> createDepartement(@Validated @RequestBody Departement departement){

        try {
            departementService.save(departement);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("{id}")
    public ResponseEntity<HttpStatus> updateDepartement(

            @PathVariable(value = "id") Long id, @Validated @RequestBody Departement departementDetails) throws DepartementNotFoundException
    {
        departementService.updateDepartement(id,departementDetails );
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus>  deleteDepartement(@PathVariable(value = "id") Long id){

        try {
            departementService.deleteDepartement(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
