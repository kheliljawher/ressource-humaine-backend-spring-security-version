package com.example.spring_security_version.controller;

import com.example.spring_security_version.entity.Candidat;
import com.example.spring_security_version.entity.Candidature;
import com.example.spring_security_version.entity.Departement;
import com.example.spring_security_version.entity.InterviewCandidat;
import com.example.spring_security_version.exception.CandidatNotFoundException;
import com.example.spring_security_version.repository.CandidatRepository;
import com.example.spring_security_version.repository.CandidatureRepository;
import com.example.spring_security_version.repository.DepartementRepository;
import com.example.spring_security_version.repository.InterviewCandidatureRepository;
import com.example.spring_security_version.secutity.AppUser;
import com.example.spring_security_version.secutity.UserService;
import com.example.spring_security_version.service.CandidatService;
import com.example.spring_security_version.service.StockageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@RestController
@RequestMapping("/api/candidat/")
@EnableSwagger2
@CrossOrigin(origins = "*")
public class CandidatController {

    @Autowired
    private CandidatService candidatService;

    @Autowired
    private DepartementRepository departementRepository;

    @Autowired
    private StockageService stockageService;

    @Autowired
    private UserService userService;

    @Autowired
    private InterviewCandidatureRepository interviewCandidatureRepository;

    @Autowired
    private CandidatureRepository candidatureRepository;

    @Autowired
    private CandidatRepository candidatRepository;

    @GetMapping("/")

    public String getMessage() {
        return "Candidat controller ...";
    }

    @Autowired
    private JavaMailSender javaMailSender;


    @PostMapping("/sendEmailInterview/{id_candidat}")
    public void SendSimpleMessage(@RequestBody InterviewCandidat interviewCandidat,@PathVariable Long id_candidat){
       // Candidature candidature = candidatureRepository.findById(id_candidature).get();
        SimpleMailMessage msg = new SimpleMailMessage();
        Candidat candidat = candidatRepository.findById(id_candidat).get();
        msg.setTo(candidat.getEmail());

        msg.setSubject("Interview ");
        msg.setText("Hello " +candidat.getNom()+ " \n date :"+interviewCandidat.getDate());

        javaMailSender.send(msg);
        interviewCandidat.setCandidat(candidat);
        interviewCandidatureRepository.save(interviewCandidat);

        System.out.println("email done");
    }

    @GetMapping("getAll")
    public List<Candidat> getAllCandidats(){

        return candidatService.findAll();

    }

    @GetMapping("{id}")
    public ResponseEntity<Candidat> getCandidatById(@PathVariable(value = "id") Long id)
            throws CandidatNotFoundException {

        Candidat candidat =
                candidatService
                        .findById(id)
                        .orElseThrow(() -> new CandidatNotFoundException(id));
        return ResponseEntity.ok().body(candidat);

    }

    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    //@ResponseHeader(description = "abc")
    public Candidat createCandidat(Candidat candidat, @RequestParam("fileCv") MultipartFile fileCv, @RequestParam("fileLettreMotivation") MultipartFile fileLettreMotivation){

        
        candidat.setRole("CANDIDAT");
        String filename = stockageService.fileName(fileCv);
        stockageService.store(fileCv,filename);
        candidat.setImage(filename);
        String filename1 = stockageService.fileName(fileLettreMotivation);
        stockageService.store(fileLettreMotivation,filename1);
        candidat.setImage(filename1);
        AppUser user = new AppUser(candidat.getLogin(),candidat.getPassword(),candidat.getRole());
        userService.save(user);
        return candidatService.save(candidat);

    }

    @PutMapping("{id_candidat}/{id_departement}")
    public ResponseEntity<HttpStatus> updateCandidat(

            @PathVariable(value = "id_candidat") Long id_candidat, @PathVariable Long id_departement, Candidat candidatDetails, @RequestParam("file") MultipartFile fileCv, @RequestParam("file") MultipartFile fileLettreMotivation) throws CandidatNotFoundException
    {

        String filename = stockageService.fileName(fileCv);
        stockageService.store(fileCv,filename);
        candidatDetails.setImage(filename);
        String filename1 = stockageService.fileName(fileLettreMotivation);
        stockageService.store(fileLettreMotivation,filename1);
        candidatDetails.setImage(filename1);
        candidatService.updateCandidat(id_candidat,candidatDetails );
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus>  deleteCandidat(@PathVariable(value = "id") Long id){

        try {
            candidatService.deleteCandidat(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/cvs/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getCv(@PathVariable String filename){
        Resource fileCv = stockageService.loadFile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileCv.getFilename() + "\"")
                .body(fileCv);
    }

    @GetMapping("/lettreMotivations/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getLettreMotivation(@PathVariable String filename){
        Resource fileLettreMotivation = stockageService.loadFile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileLettreMotivation.getFilename() + "\"")
                .body(fileLettreMotivation);
    }

}
