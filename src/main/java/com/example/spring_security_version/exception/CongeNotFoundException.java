package com.example.spring_security_version.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)

public class CongeNotFoundException extends Exception{

    public CongeNotFoundException(long id) {
        super("le conge ayant l'id :"+id+" n'existe pas");
    }

}
