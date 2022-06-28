package com.example.spring_security_version.controller;

import com.example.spring_security_version.entity.ChefDepartement;
import com.example.spring_security_version.entity.Departement;
import com.example.spring_security_version.entity.Employe;
import com.example.spring_security_version.entity.Planning;
import com.example.spring_security_version.exception.ChefDepartementNotFoundException;
import com.example.spring_security_version.repository.ChefDepartementRepository;
import com.example.spring_security_version.repository.DepartementRepository;
import com.example.spring_security_version.repository.PlanningRepository;
import com.example.spring_security_version.service.ChefDepartementService;
import com.example.spring_security_version.service.StockageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@RestController
@RequestMapping("/api/chefDepartement/")
@EnableSwagger2
@CrossOrigin(origins = "*")
public class ChefDepartementController {

    @Autowired
    private ChefDepartementService chefDepartementService;

    @Autowired
    private StockageService stockageService;

    @Autowired
    private ChefDepartementRepository chefDepartementRepository;

    @Autowired
    private PlanningRepository planningRepository;

    @Autowired
    private DepartementRepository departementRepository;

    @GetMapping("/")

    public String getMessage() {
        return "Chef de departement controller ...";
    }

    @GetMapping("getAll")
    public List<ChefDepartement> getAllChefDepartements(){

        return chefDepartementService.findAll();

    }

    @GetMapping("{id}")
    public ResponseEntity<ChefDepartement> getChefDepartementById(@PathVariable(value = "id") Long id)
            throws ChefDepartementNotFoundException {

        ChefDepartement chefDepartement =
                chefDepartementService
                        .findById(id)
                        .orElseThrow(() -> new ChefDepartementNotFoundException(id));
        return ResponseEntity.ok().body(chefDepartement);

    }

    @PostMapping("create/{id_planning}/{id_departement}")
    @ResponseStatus(HttpStatus.CREATED)
    //@ResponseHeader(description = "abc")
    public ResponseEntity<HttpStatus> createChefDepartement(ChefDepartement chefDepartement, @PathVariable Long id_planning, @PathVariable Long id_departement, @RequestParam("file") MultipartFile file){

        try {
            chefDepartement.setActif("OUI");
            Planning planning=planningRepository.findById(id_planning).get();
            chefDepartement.setPlanning(planning);
            Departement departement=departementRepository.findById(id_departement).get();
            chefDepartement.setDepartement(departement);
            String filename = stockageService.fileName(file);
            stockageService.store(file,filename);
            chefDepartement.setImage(filename);
            chefDepartement.setRole("CHEFDEPARTEMENT");
            chefDepartementService.save(chefDepartement);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("{id}/{id_planning}/{id_departement}")
    public ResponseEntity<HttpStatus> updateChefDepartement(

            @PathVariable(value = "id") Long id, @PathVariable Long id_planning,  @PathVariable Long id_departement, @RequestParam("file") MultipartFile file,ChefDepartement chefDepartementDetails) throws ChefDepartementNotFoundException
    {
        if(file != null){
            System.out.println("here if");
            chefDepartementDetails.setStatus("oui");
            Planning planning=planningRepository.findById(id_planning).get();
            chefDepartementDetails.setPlanning(planning);
            Departement departement=departementRepository.findById(id_departement).get();
            chefDepartementDetails.setDepartement(departement);
            String filename = stockageService.fileName(file);
            stockageService.store(file,filename);
            chefDepartementDetails.setImage(filename);
        } else {
            System.out.println("here else");
            chefDepartementDetails.setImage(chefDepartementRepository.findById(id).orElse(null).getImage());
        }
        chefDepartementService.updateChefDepartement(id,chefDepartementDetails );
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping("/setStatusNoActive/{id_chef_departement}")
    public ChefDepartement updateStatus(@PathVariable Long id_chef_departement){

        ChefDepartement chef = chefDepartementRepository.findById(id_chef_departement).orElse(null);
        chef.setActif("noActive");
        return chefDepartementRepository.save(chef);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus>  deleteChefDepartement(@PathVariable(value = "id") Long id){

        try {
            chefDepartementService.deleteChefDepartement(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename){
        Resource file = stockageService.loadFile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

}
