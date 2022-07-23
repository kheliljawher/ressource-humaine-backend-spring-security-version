package com.example.spring_security_version.controller;

import com.example.spring_security_version.entity.*;
import com.example.spring_security_version.exception.EmployeNotFoundException;
import com.example.spring_security_version.repository.CongeRepository;
import com.example.spring_security_version.repository.DepartementRepository;
import com.example.spring_security_version.repository.EmployeRepository;
import com.example.spring_security_version.repository.PlanningRepository;
import com.example.spring_security_version.secutity.AppUser;
import com.example.spring_security_version.secutity.UserService;
import com.example.spring_security_version.service.EmployeService;
import com.example.spring_security_version.service.PlanningService;
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

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employe/")
@EnableSwagger2
@CrossOrigin(origins = "*")
public class EmployeController {

    @Autowired
    private EmployeService employeService;

    @Autowired
    private StockageService stockageService;

    @Autowired
    private CongeRepository congeRepository;

    @Autowired
    private EmployeRepository employeRepository;

    @Autowired
    private PlanningRepository planningRepository;

    @Autowired
    private PlanningService planningService;

    @Autowired
    private DepartementRepository departementRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/")

    public String getMessage() {
        return "Employe controller ...";
    }

    @GetMapping("getAll")
    public List<Employe> getAllEmployes(){

        return employeService.findAll();

    }

    @GetMapping("{id}")
    public ResponseEntity<Employe> getEmployeById(@PathVariable(value = "id") Long id)
            throws EmployeNotFoundException {

        Employe employe =
                employeService
                        .findById(id)
                        .orElseThrow(() -> new EmployeNotFoundException(id));
        return ResponseEntity.ok().body(employe);

    }

    /*@GetMapping("{nom}")
    public ResponseEntity<Employe> getEmployeByNom(@PathVariable(value = "nom") String nom)
            throws EmployeNotFoundException {

        Employe employe =
                employeService
                        .findByNom(nom).get();
        //.orElseThrow(() -> new EmployeeNotFoundException(nom));
        return ResponseEntity.ok().body(employe);

    }

    @GetMapping("{prenom}")
    public ResponseEntity<Employe> getEmployeByPrenom(@PathVariable(value = "prenom") String prenom)
            throws EmployeNotFoundException {

        Employe employe =
                employeService
                        .findByNom(prenom).get();
        //.orElseThrow(() -> new EmployeeNotFoundException(prenom));
        return ResponseEntity.ok().body(employe);

    }*/

    @PostMapping("create/{id_planning}/{id_departement}")
    @ResponseStatus(HttpStatus.CREATED)
    //@ResponseHeader(description = "abc")
    public ResponseEntity<HttpStatus> createEmploye(Employe employe, @PathVariable Long id_planning, @PathVariable Long id_departement, @RequestParam("file") MultipartFile file){

        try {
            employe.setActif("OUI");
            Planning planning=planningRepository.findById(id_planning).get();
            employe.setPlanning(planning);
            Departement departement=departementRepository.findById(id_departement).get();
            employe.setDepartement(departement);
            String filename = stockageService.fileName(file);
            stockageService.store(file,filename);
            employe.setRole("EMPLOYE");
            employe.setImage(filename);

           /* SimpleDateFormat dateParser = new SimpleDateFormat ("dd/MM/yyyy"); //Format for input
            String date=employe.getDate_Embauche();
            java.util.Date dn = dateParser.parse(date); //Parsing the date
            SimpleDateFormat dateFormatter = new SimpleDateFormat ("dd-MM-yyyy"); //Format for output
            employe.setDate(dateFormatter.format(dn)); //Printing the date
            */
            AppUser user = new AppUser(employe.getLogin(),employe.getPassword(),employe.getRole());
            userService.save(user);
            employeService.save(employe);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

   /* @PostMapping("create/{id_planning}/{id_departement}")
    @ResponseStatus(HttpStatus.CREATED)
    //@ResponseHeader(description = "abc")
    public ResponseEntity<HttpStatus> createEmployeWithoutImage(@PathVariable Long id_planning, @PathVariable Long id_departement, @RequestBody Employe employe){

        try {
            employe.setActif("OUI");
            Planning planning=planningRepository.findById(id_planning).get();
            employe.setPlanning(planning);
            Departement departement=departementRepository.findById(id_departement).get();
            employe.setDepartement(departement);
            employe.setRole("EMPLOYE");

            AppUser user = new AppUser(employe.getLogin(),employe.getPassword(),employe.getRole());
            userService.save(user);
            employeService.save(employe);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }*/

    @PutMapping("{id_employe}/{id_planning}/{id_departement}")
    public Employe updateEmploye(@PathVariable(value = "id_employe") Long id_employe, @PathVariable Long id_planning,  @PathVariable Long id_departement, @RequestParam("file") MultipartFile file, Employe employeDetails) throws EmployeNotFoundException
    {
        if(file != null){
            System.out.println("here "+id_planning);
            employeDetails.setStatus("oui");
            Planning planning=planningRepository.findById(id_planning).get();
            employeDetails.setPlanning(planning);
            Departement departement=departementRepository.findById(id_departement).get();
            employeDetails.setDepartement(departement);
            String filename = stockageService.fileName(file);
            stockageService.store(file,filename);
            employeDetails.setImage(filename);
            System.out.println("here planning in employe "+employeDetails.getPlanning().getId());
            System.out.println("here departement in employe "+employeDetails.getDepartement().getId());


        } else {
            System.out.println("here else");
            employeDetails.setImage(employeRepository.findById(id_employe).orElse(null).getImage());
        }

        return employeService.updateEmploye(id_employe,employeDetails);

    }

    @GetMapping("/setStatusNoActive/{id_employe}")
    public Employe updateStatus(@PathVariable Long id_employe){

        Employe e = employeRepository.findById(id_employe).orElse(null);
        e.setActif("noActive");
        return employeRepository.save(e);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteEmploye(@PathVariable(value = "id") Long id){

        try {
            employeService.deleteEmploye(id);
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

    @GetMapping("/updatePlanning/{id_employe}/{id_plannig}")
    @ResponseBody
    public void getEmployeById(@PathVariable(value = "id_employe") Long id_employe, @PathVariable(value = "id_plannig") Long id_plannig)
    {
        Employe employe = employeService.findEmployeById(id_employe);
        Planning planning = planningService.findPlanningById(id_plannig);
        employe.setPlanning(planning);
        employeRepository.save(employe);
    }
/*
    @GetMapping("/id_chef_departement/id_plannig")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename){
        Resource file = stockageService.loadFile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }*/


}
