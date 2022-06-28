package com.example.spring_security_version.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
@Entity
public class Employe extends Utilisateur{

    //@OneToMany(mappedBy = "employe")
    //@Getter( onMethod = @__(@JsonIgnore))
    //@JsonIgnore
    //private List<Conge> conges;



    /*@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_contrat", referencedColumnName = "id")
    private Contrat contrat;*/

}
