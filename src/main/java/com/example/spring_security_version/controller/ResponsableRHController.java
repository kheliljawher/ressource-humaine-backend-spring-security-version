package com.example.spring_security_version.controller;

import com.example.spring_security_version.entity.ResponsableRH;
import com.example.spring_security_version.entity.Utilisateur;
import com.example.spring_security_version.exception.ResponsableRHNotFoundException;
import com.example.spring_security_version.exception.UtilisateurNotFoundException;
import com.example.spring_security_version.service.ResponsableRHService;
import com.example.spring_security_version.service.StockageService;
import com.example.spring_security_version.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@RestController
@RequestMapping("/api/responsableRH/")
@EnableSwagger2
@CrossOrigin(origins = "*")
public class ResponsableRHController {

    @Autowired
    private ResponsableRHService responsableRHService;

    @Autowired
    private StockageService stockageService;

    @GetMapping("/")

    public String getMessage() {
        return "Responsable RH controller ...";
    }

    @GetMapping("getAll")
    public List<ResponsableRH> getAllResponsableRHs(){

        return responsableRHService.findAll();

    }

    @GetMapping("{id}")
    public ResponseEntity<ResponsableRH> getResponsableRHById(@PathVariable(value = "id") Long id)
            throws ResponsableRHNotFoundException {

        ResponsableRH responsableRH =
                responsableRHService
                        .findById(id)
                        .orElseThrow(() -> new ResponsableRHNotFoundException(id));
        return ResponseEntity.ok().body(responsableRH);

    }

    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    //@ResponseHeader(description = "abc")
    public ResponseEntity<HttpStatus> createResponsableRH(@Validated @RequestBody ResponsableRH responsableRH){

        try {
            responsableRH.setRole("RESPONSABLERH");
            responsableRHService.save(responsableRH);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("{id}")
    public ResponseEntity<HttpStatus> updateResponsableRH(

            @PathVariable(value = "id") Long id, @Validated @RequestBody ResponsableRH responsableRHDetails) throws ResponsableRHNotFoundException
    {
        responsableRHService.updateResponsableRH(id,responsableRHDetails );
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus>  deleteResponsableRH(@PathVariable(value = "id") Long id){

        try {
            responsableRHService.deleteResponsableRH(id);
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
