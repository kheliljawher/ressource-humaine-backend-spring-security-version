package com.example.spring_security_version.controller;

import com.example.spring_security_version.entity.Utilisateur;
import com.example.spring_security_version.exception.UtilisateurNotFoundException;
import com.example.spring_security_version.repository.UtilisateurRepository;
import com.example.spring_security_version.service.StockageService;
import com.example.spring_security_version.service.UtilisateurService;
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
@RequestMapping("/api/utilisateur/")
@EnableSwagger2
@CrossOrigin(origins = "*")
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private StockageService stockageService;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @GetMapping("/")

    public String getMessage() {
        return "Utilisateur controller ...";
    }

    @GetMapping("getAll")
    public List<Utilisateur> getAllUtilisateurs(){

        return utilisateurService.findAll();

    }

    @GetMapping("{id}")
    public ResponseEntity<Utilisateur> getUtilisateurById(@PathVariable(value = "id") Long id)
            throws UtilisateurNotFoundException {

        Utilisateur utilisateur =
                utilisateurService
                        .findById(id)
                        .orElseThrow(() -> new UtilisateurNotFoundException(id));
        return ResponseEntity.ok().body(utilisateur);

    }

    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    //@ResponseHeader(description = "abc")
    public ResponseEntity<HttpStatus> createUtilisateur(@RequestParam("file") MultipartFile file, @Validated Utilisateur utilisateur) {

        try {
            String filename = stockageService.fileName(file);
            stockageService.store(file,filename);
            utilisateur.setImage(filename);
            utilisateurService.save(utilisateur);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("{id}")
    public ResponseEntity<HttpStatus> updateUtilisateur(

            @PathVariable(value = "id") Long id, @Validated @RequestBody Utilisateur utilisateurDetails) throws UtilisateurNotFoundException
    {
        utilisateurService.updateUtilisateur(id,utilisateurDetails );
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteUtilisateur(@PathVariable(value = "id") Long id){

        try {
            utilisateurService.deleteUtilisateur(id);
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

    @PostMapping("login")
    @ResponseStatus(HttpStatus.CREATED)
    //@ResponseHeader(description = "abc")
    public Utilisateur loginUtilisateur(@RequestBody @Validated Utilisateur utilisateur) {

            return utilisateurRepository.getAllUtilisateurs(utilisateur.getLogin(),utilisateur.getPassword());

    }



}
