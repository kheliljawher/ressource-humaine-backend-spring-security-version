package com.example.spring_security_version.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)

public class CandidatureNotFoundException extends Exception{

    public CandidatureNotFoundException(long id) {
        super("le candidature ayant l'id :"+id+" n'existe pas");
    }

}
