package com.example.spring_security_version.controller;

import com.example.spring_security_version.entity.ChefDepartement;
import com.example.spring_security_version.entity.Employe;
import com.example.spring_security_version.entity.Planning;
import com.example.spring_security_version.exception.PlanningNotFoundException;
import com.example.spring_security_version.repository.ChefDepartementRepository;
import com.example.spring_security_version.repository.EmployeRepository;
import com.example.spring_security_version.service.PlanningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@RestController
@RequestMapping("/api/planning/")
@EnableSwagger2
@CrossOrigin(origins = "*")
public class PlanningController {

    @Autowired
    private PlanningService planningService;

    @Autowired
    private EmployeRepository employeRepository;

    @Autowired
    private ChefDepartementRepository chefDepartementRepository;

    @GetMapping("/")

    public String getMessage() {
        return "Planning controller ...";
    }

    @GetMapping("getAll")
    public List<Planning> getAllPlannings(){

        return planningService.findAll();

    }

    @GetMapping("{id}")
    public ResponseEntity<Planning> getPlanningById(@PathVariable(value = "id") Long id)
            throws PlanningNotFoundException {

        Planning planning =
                planningService
                        .findById(id)
                        .orElseThrow(() -> new PlanningNotFoundException(id));
        return ResponseEntity.ok().body(planning);

    }
///{id_chefDepartement}
    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    //@ResponseHeader(description = "abc") //, @PathVariable Long id_chefDepartement
    public ResponseEntity<HttpStatus> createPlanning(@Validated @RequestBody Planning planning){

        try {
            /*ChefDepartement chefDepartement=chefDepartementRepository.findById(id_chefDepartement).get();
            planning.setChef_departement(chefDepartement);
            System.out.println(chefDepartement);*/
            planningService.save(planning);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("{id_planning}")
    public ResponseEntity<HttpStatus> updatePlanning(

            @PathVariable(value = "id_planning") Long id_planning, @Validated @RequestBody Planning planningDetails) throws PlanningNotFoundException
    {

        planningService.updatePlanning(id_planning,planningDetails );
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus>  deletePlanning(@PathVariable(value = "id") Long id){

        try {
            planningService.deletePlanning(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
